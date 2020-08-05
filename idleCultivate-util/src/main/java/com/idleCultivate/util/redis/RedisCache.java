package com.idleCultivate.util.redis;

import com.idleCultivate.util.serialize.KryoPoolSerializer;
import com.idleCultivate.util.serialize.Serializer;
import com.idleCultivate.util.serialize.StringSerializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ListPosition;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisCache {
    private static final Logger logger = LogManager.getLogger(RedisCache.class);

    private JedisPool pool;
    private Serializer<String> stringSerializer;
    private Serializer<Object> valueSerializer;
    private String keyPrefix;
    private final String OK = "OK";

    public RedisCache() {
        this.stringSerializer = new StringSerializer();
        this.valueSerializer = new KryoPoolSerializer();
    }

    public RedisCache(JedisPool pool) {
        this();
        this.pool = pool;
    }

    public RedisCache(JedisPool pool, String keyPrefix) {
        this(pool);
        this.keyPrefix = keyPrefix;
    }

    /* 生成key */
    public String genkey(String... segments) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(this.keyPrefix)) {
            builder.append(this.keyPrefix);
        }

        for (String segment : segments) {
            builder.append(":");
            builder.append(segment);
        }

        return builder.toString();
    }

    /* 自定义存储Object */
    public Boolean cache(String key, Object obj) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            byte[] keyBuffer = null;
            byte[] valueBuffer = null;
            try {
                keyBuffer = this.stringSerializer.serialize(key);
                valueBuffer = this.valueSerializer.serialize(obj);
            } catch (Exception ex) {
                logger.error("序列化出错：" + ex.getMessage(), ex);
                return false;
            }

            return conn.set(keyBuffer, valueBuffer).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean cache(String key, Object obj, int seconds) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            byte[] keyBuffer = null;
            byte[] valueBuffer = null;
            try {
                keyBuffer = this.stringSerializer.serialize(key);
                valueBuffer = this.valueSerializer.serialize(obj);
            } catch (Exception ex) {
                logger.error("序列化出错：" + ex.getMessage(), ex);
                return false;
            }

            return conn.setex(keyBuffer, seconds, valueBuffer).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Object getCache(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            byte[] keyBuffer = null;
            byte[] valueBuffer = null;
            try {
                keyBuffer = this.stringSerializer.serialize(key);
                valueBuffer = conn.get(keyBuffer);
                return this.valueSerializer.deserialize(valueBuffer);
            } catch (Exception ex) {
                logger.error("(反)序列化出错：" + ex.getMessage(), ex);
                return null;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* key操作 */
    public Set<String> keys(String pattern) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.keys(pattern);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean exists(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.exists(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean expire(String key, int seconds) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.expire(key, seconds) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean expireAt(String key, Date date) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.expireAt(key, date.getTime() / 1000L) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long ttl(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.ttl(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean del(String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.del(keys) > 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String type(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.type(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* Strinng操作 */

    public String get(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.get(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getSet(String key, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.getSet(key, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String getrange(String key, long start, long end) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.getrange(key, start, end);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> mget(String keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.mget(keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean set(String key, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.set(key, value).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean setex(String key, String value, int seconds) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.setex(key, seconds, value).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean setnx(String key, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.setnx(key, value) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long setrange(String key, long offset, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.setrange(key, offset, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean mset(String... keysvalues) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.mset(keysvalues).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean msetnx(String... keysvalues) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.msetnx(keysvalues).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long strlen(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.strlen(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long incr(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.incr(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long incrBy(String key, long increment) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.incrBy(key, increment);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long decr(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.decr(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long decrBy(String key, long decrement) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.decrBy(key, decrement);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long append(String key, String content) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.append(key, content);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* Hash操作 */

    public Set<String> hkeys(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hkeys(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> hvals(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hvals(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long hlen(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hlen(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean hexists(String key, String field) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hexists(key, field);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean hdel(String key, String fields) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hdel(key, fields) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Map<String, String> hgetall(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hgetAll(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String hget(String key, String field) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hget(key, field);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> hmget(String key, String... fields) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hmget(key, fields);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long hset(String key, String field, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hset(key, field, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean hsetnx(String key, String field, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hsetnx(key, field, value) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean hmset(String key, Map<String, String> hash) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hmset(key, hash).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long hincrBy(String key, String field, Long increment) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.hincrBy(key, field, increment);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* List操作 */

    public Long llen(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.llen(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String lindex(String key, long index) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lindex(key, index);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long lrem(String key, String value, long count) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lrem(key, count, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean lset(String key, String value, long index) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lset(key, index, value).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String lpop(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lpop(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String rpop(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.rpop(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long lpush(String key, String... values) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lpush(key, values);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long rpush(String key, String... values) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.rpush(key, values);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long lpushx(String key, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lpushx(key, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long rpushx(String key, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.rpushx(key, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String rpoplpush(String srckey, String dstkey) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.rpoplpush(srckey, dstkey);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> lrange(String key, long start, long stop) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.lrange(key, start, stop);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long linsert(String key, ListPosition where, String pivot, String value) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.linsert(key, where, pivot, value);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean ltrim(String key, long start, long stop) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.ltrim(key, start, stop).equals(OK);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /* Set操作 */

    public Long sadd(String key, String... members) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sadd(key, members);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long scard(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.scard(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean sismember(String key, String member) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sismember(key, member);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Set<String> smembers(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.smembers(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Set<String> sinter(String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sinter(keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long sinterstore(String dstkey, String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sinterstore(dstkey, keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Set<String> sunion(String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sunion(keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long sunionstore(String dstkey, String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sunionstore(dstkey, keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Set<String> sdiff(String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sdiff(keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long sdiffstore(String dstkey, String... keys) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.sdiffstore(dstkey, keys);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String srandmember(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.srandmember(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<String> srandmember(String key, int count) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.srandmember(key, count);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Boolean smove(String srckey, String dstkey, String member) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.smove(srckey, dstkey, member) == 1;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String spop(String key) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.spop(key);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Long srem(String key, String... members) {
        Jedis conn = null;
        try {
            conn = this.pool.getResource();
            return conn.srem(key, members);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
