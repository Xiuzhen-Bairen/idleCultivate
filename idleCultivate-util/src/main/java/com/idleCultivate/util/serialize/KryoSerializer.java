package com.idleCultivate.util.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoSerializer {
    private Kryo kryo;
    private Output output;
    private Input input;

    KryoSerializer() {
        this.kryo = new Kryo();
        this.kryo.setRegistrationRequired(false);
        this.kryo.setMaxDepth(20);
        this.output = new Output(1024, -1);
        this.input = new Input();
    }

    public byte[] serialize(Object obj) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("传入参数不能为NULL！");
        }

        this.output.reset();
        this.kryo.writeClassAndObject(this.output, obj);
        return this.output.toBytes();
    }

    public Object deserialize(byte[] bytes) throws Exception {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        this.input.reset();
        this.input.setBuffer(bytes, 0, bytes.length);
        return this.kryo.readClassAndObject(this.input);
    }
}
