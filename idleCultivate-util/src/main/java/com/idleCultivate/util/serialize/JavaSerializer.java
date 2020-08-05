package com.idleCultivate.util.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerializer implements Serializer<Object> {
    public byte[] serialize(Object obj) throws Exception {
        ObjectOutputStream outputStream = null;
        ByteArrayOutputStream byteStream = null;
        try {
            byteStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(byteStream);
            outputStream.writeObject(obj);
            return byteStream.toByteArray();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception ex) {
                }
            }

            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public Object deserialize(byte[] bytes) throws Exception {
        if ((bytes == null) || (bytes.length == 0)) {
            return null;
        }

        ObjectInputStream inputStream = null;
        ByteArrayInputStream byteStream = null;
        try {
            byteStream = new ByteArrayInputStream(bytes);
            inputStream = new ObjectInputStream(byteStream);
            return inputStream.readObject();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                }
            }

            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}