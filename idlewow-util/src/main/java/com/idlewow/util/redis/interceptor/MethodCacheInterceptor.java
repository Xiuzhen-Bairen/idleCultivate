package com.idlewow.util.redis.interceptor;

import com.idlewow.util.redis.RedisCache;
import com.idlewow.util.redis.annotation.Cache;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;

public class MethodCacheInterceptor implements MethodInterceptor {
    private static final Logger logger = LogManager.getLogger(MethodCacheInterceptor.class);

    private RedisCache redisCache;
    private Executor executor;

    public MethodCacheInterceptor(RedisCache redisCache, Executor executor) {
        this.redisCache = redisCache;
        this.executor = executor;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Cache annotation = invocation.getMethod().getAnnotation(Cache.class);
        if (annotation == null) {
            return invocation.proceed();
        }

        Object value = null;
        String key;
        if (StringUtils.isNotBlank(annotation.key())) {
            key = annotation.key();
        } else {
            String targetName = invocation.getThis().getClass().getName();
            String methodName = invocation.getMethod().getName();
            Object[] args = invocation.getArguments();
            key = this.getCacheKey(targetName, methodName, args);
        }

        try {
            if (this.redisCache.exists(key)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("[Redis Cache]缓存数据存在, 从Redis缓存中获取, key=" + key);
                }

                value = this.redisCache.getCache(key);
            } else {
                value = invocation.proceed();
                if (value != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("[Redis Cache]写入缓存, key=" + key + ", value=" + value);
                    }

                    this.setCache(key, value, annotation.time());
                }
            }
        } catch (Exception ex) {
            logger.error("[Redis Cache]缓存操作异常: " + ex.getMessage(), ex);
            if (value == null) {
                return invocation.proceed();
            }
        }

        return value;
    }

    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuilder builder = new StringBuilder();
        builder.append(targetName).append(":").append(methodName);
        if (arguments != null && arguments.length > 0) {
            for (Object argument : arguments) {
                builder.append(":").append(argument);
            }
        }

        return builder.toString();
    }

    private void setCache(final String key, final Object value, final int seconds) {
        this.executor.execute(() -> {
            try {
                if (seconds == 0) {
                    MethodCacheInterceptor.this.redisCache.cache(key, value);
                } else {
                    MethodCacheInterceptor.this.redisCache.cache(key, value, seconds);
                }
            } catch (Exception ex) {
                MethodCacheInterceptor.logger.error("[Redis Cache]缓存写入异常", ex);
            }
        });
    }
}
