package com.idleCultivate.game.server.dispatch;

import com.idleCultivate.game.server.message.SocketMessage;
import io.netty.channel.ChannelHandlerContext;

public interface DispatchService {
    void dispatch(ChannelHandlerContext ctx, SocketMessage message);
}
