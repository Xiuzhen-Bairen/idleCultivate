package com.idlewow.util.serialize;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class KryoPoolSerializer implements Serializer<Object> {
    private static final int PoolSize = 10;
    private static LinkedBlockingQueue<KryoSerializer> queue;

    static {
        queue = new LinkedBlockingQueue<>(PoolSize);
        for (int i = 1; i <= PoolSize; i++) {
            KryoSerializer serializer = new KryoSerializer();
            queue.offer(serializer);
        }
    }

    public byte[] serialize(Object obj) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("传入参数不能为NULL！");
        }

        KryoSerializer serializer = null;
        try {
            serializer = queue.poll(5000, TimeUnit.MILLISECONDS);
            if (serializer == null) {
                throw new Exception("序列化获取serializer超时");
            }

            return serializer.serialize(obj);
        } finally {
            if (serializer != null) {
                queue.offer(serializer);
            }
        }
    }

    public Object deserialize(byte[] bytes) throws Exception {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        KryoSerializer serializer = null;
        try {
            serializer = queue.take();
            return serializer.deserialize(bytes);
        } finally {
            if (serializer != null) {
                queue.offer(serializer);
            }
        }
    }
}
