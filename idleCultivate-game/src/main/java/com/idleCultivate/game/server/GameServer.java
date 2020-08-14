package com.idleCultivate.game.server;

import com.idleCultivate.game.decoder.LengthDecoder;
import com.idleCultivate.game.decoder.MessageDecoder;
import com.idleCultivate.game.encoder.MessageEncoder;
import com.idleCultivate.game.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

public class GameServer {

    private static final Logger log = LogManager.getLogger(GameServer.class);
    private final EventLoopGroup bossGroup;//监听SeverChannel
    private final EventLoopGroup workerGroup;//创建所有客户端Channel
    private final ServerBootstrap bootstrap;//netty服务端启动类

    public GameServer() {
        bossGroup = new NioEventLoopGroup();//线程组
        workerGroup = new NioEventLoopGroup(4);
        bootstrap = new ServerBootstrap();//netty服务端启动类，与客户端不同
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)//绑定服务端通道，与客户端不同
                .option(ChannelOption.SO_BACKLOG, 1024)//指定客户端连接请求队列大小
                .childOption(ChannelOption.TCP_NODELAY, true);//关闭nagle算法，实时性高的游戏不需延迟粘包
    }

    public void bind(String ip, int port) throws Exception
    {
        try {
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //添加业务处理handler
                    ch.pipeline().addLast(new LengthDecoder(1024,0,4,0,4));
                    ch.pipeline().addLast(new MessageDecoder());//解码器，将二进制字节流解码成游戏自定义协议包
                    ch.pipeline().addLast(new MessageEncoder());//编码器，将游戏业务数据编码为二进制字节流下发给客户端
                    ch.pipeline().addLast(new ServerHandler());//业务处理handler
                }
            });
            InetSocketAddress address = new InetSocketAddress(ip, port);
            ChannelFuture f = bootstrap.bind(address).sync();
            if (f.isSuccess())
            {
                System.out.println("Server starts success at port:" + port);
            }
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("bind "+ip+":"+port+" failed", e);
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception
    {
        String ip = "localhost";
        int port = 9888;
        new GameServer().bind(ip, port);
    }
}