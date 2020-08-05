package com.idleCultivate.util.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedisLock {
    private static final Logger logger = LogManager.getLogger(RedisLock.class);

    private RedisCache redisCache;
    private String lockKey;
    private int expireMsecs;
    private int timeoutMsecs;
    private volatile boolean locked;

    private RedisLock(RedisCache redisCache, String lockKey) {
        this.expireMsecs = 600000;
        this.timeoutMsecs = 0;
        this.locked = false;
        this.redisCache = redisCache;
        this.lockKey = "lock:" + lockKey;
    }

    public RedisLock(RedisCache redisCache, String lockKey, int expireMsecs) {
        this(redisCache, lockKey);
        this.expireMsecs = expireMsecs;
    }

    public RedisLock(RedisCache redisCache, String lockKey, int expireMsecs, int timeoutMsecs) {
        this(redisCache, lockKey, expireMsecs);
        this.timeoutMsecs = timeoutMsecs;
    }

    public synchronized boolean lock() throws InterruptedException {
        int timeout = this.timeoutMsecs;

        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + (long) this.expireMsecs + 1L;
            String expiresStr = String.valueOf(expires);
            if (this.redisCache.setnx(this.lockKey, expiresStr)) {
                this.locked = true;
                return true;
            }

            String currentValueStr = this.redisCache.get(this.lockKey);
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                String oldValueStr = this.redisCache.getSet(this.lockKey, expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    this.locked = true;
                    return true;
                }
            }

            timeout -= 100;
            Thread.sleep(100L);
        }

        return false;
    }

    public synchronized void unlock() {
        if (this.locked) {
            this.redisCache.del(this.lockKey);
            this.locked = false;
        }
    }
}
