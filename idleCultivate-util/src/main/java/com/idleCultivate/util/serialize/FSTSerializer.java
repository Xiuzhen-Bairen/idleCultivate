package com.idleCultivate.util.serialize;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FSTSerializer implements Serializer<Object> {
    public byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream byteStream = null;
        FSTObjectOutput fstOutput = null;
        try {
            byteStream = new ByteArrayOutputStream();
            fstOutput = new FSTObjectOutput(byteStream);
            fstOutput.writeObject(obj);
            fstOutput.flush();
            return byteStream.toByteArray();
        } finally {
            if (fstOutput != null) {
                try {
                    fstOutput.close();
                } catch (IOException ex) {
                }
            }

            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public Object deserialize(byte[] bytes) throws Exception {
        if ((bytes == null) || (bytes.length == 0)) {
            return null;
        }

        FSTObjectInput fstInput = null;
        ByteArrayInputStream byteStream = null;
        try {
            byteStream = new ByteArrayInputStream(bytes);
            fstInput = new FSTObjectInput(byteStream);
            return fstInput.readObject();
        } finally {
            if (fstInput != null) {
                try {
                    fstInput.close();
                } catch (IOException ex) {
                }
            }

            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}