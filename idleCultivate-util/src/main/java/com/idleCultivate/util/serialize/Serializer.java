package com.idleCultivate.util.serialize;

public interface Serializer<T> {
    byte[] serialize(T paramT) throws Exception;

    T deserialize(byte[] paramArrayOfByte) throws Exception;
}
