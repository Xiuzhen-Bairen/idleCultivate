package com.idleCultivate.game.handler;

import com.idleCultivate.game.login.LoginDispatch;
import com.idleCultivate.game.util.SocketModel;
import com.idleCultivate.game.util.TypeProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LogManager.getLogger(ServerHandler.class);

    //value值实际为另一包装Channel的对象，这里避免引入太多业务逻辑，简化处理了
    private final ConcurrentMap<Channel, Channel> ref = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception  //当客户端连上服务器的时候会触发此函数
    {
        super.channelActive(ctx);
        log.info("["+ctx.channel().remoteAddress()+"] connected");
        System.out.println("clinet:" + ctx.channel().id() + " join server");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception//当客户端断开连接的时候触发函数
    {
        super.channelInactive(ctx);
        log.info("["+ctx.channel().remoteAddress()+"] disconnected");
        System.out.println("clinet:" + ctx.channel().id() + " leave server");
        //User.onlineUser.remove(LoginDispatch.getInstance().user);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception//当客户端发送数据到服务器会触发此函数
    {
        SocketModel message = (SocketModel) msg;
        switch (message.getType()) {
            case TypeProtocol.TYPE_LOGIN:
                LoginDispatch.getInstance().dispatch(ctx, message);//分派登录消息
                break;
            case TypeProtocol.TYPE_WIZARD:
//                WizardDispatch.getInstance().dispatch(ctx, message);
                break;
            case TypeProtocol.TYPE_USER:
//                UserDispatch.getInstance().dispatch(ctx, message);
                break;
            case TypeProtocol.TYPE_BATTLE:
//                BattleDispatch.getInstance().dispatch(ctx, message);
            default:
                break;
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        //cause.printStackTrace();
    }
}