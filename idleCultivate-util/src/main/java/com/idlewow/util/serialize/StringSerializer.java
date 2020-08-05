package com.idlewow.util.serialize;

import java.nio.charset.Charset;

public class StringSerializer implements Serializer<String> {
    private final Charset charset;

    public StringSerializer() {
        this(Charset.forName("UTF8"));
    }

    public StringSerializer(Charset charset) {
        this.charset = charset;
    }

    public byte[] serialize(String string) throws Exception {
        return string == null ? null : string.getBytes(this.charset);
    }

    public String deserialize(byte[] bytes) throws Exception {
        return bytes == null ? null : new String(bytes, this.charset);
    }
}