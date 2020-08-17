package com.idleCultivate.game.server.decoder;

import com.idleCultivate.game.server.message.SocketMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    private Schema<SocketMessage> schema = RuntimeSchema.getSchema(SocketMessage.class);//protostuff的写法
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> obj) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        SocketMessage message = new SocketMessage();
        ProtobufIOUtil.mergeFrom(data, message, schema);
        obj.add(message);
    }

}
