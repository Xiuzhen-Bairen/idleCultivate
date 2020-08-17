package com.idleCultivate.game.server;

import com.idleCultivate.game.server.decoder.LengthDecoder;
import com.idleCultivate.game.server.decoder.MessageDecoder;
import com.idleCultivate.game.server.encoder.MessageEncoder;
import com.idleCultivate.game.server.handler.ServerHandler;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.InetSocketAddress;

public class GameServer {

    private static final Logger logger = LogManager.getLogger(GameServer.class);

    public void bind(String ip, int port, ServerHandler serverHandler) throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//线程组 监听SeverChannel
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);//创建所有客户端Channel
        ServerBootstrap bootstrap = new ServerBootstrap();//netty服务端启动类，与客户端不同
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//绑定服务端通道，与客户端不同
                    .option(ChannelOption.SO_BACKLOG, 1024)//指定客户端连接请求队列大小
                    .childOption(ChannelOption.TCP_NODELAY, true)//关闭nagle算法，实时性高的游戏不需延迟粘包
                    .childOption(ChannelOption.SO_KEEPALIVE, true);// 保持长连接
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //添加业务处理handler
                    ch.pipeline().addLast(new LengthDecoder(1024,0,4,0,4));
                    ch.pipeline().addLast(new MessageDecoder());//解码器，将二进制字节流解码成游戏自定义协议包
                    ch.pipeline().addLast(new MessageEncoder());//编码器，将游戏业务数据编码为二进制字节流下发给客户端
                    ch.pipeline().addLast(serverHandler);//业务处理handler
                }
            });
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
            ChannelFuture f = bootstrap.bind(inetSocketAddress).sync();
            if (f.isSuccess())
            {
                logger.info("Server starts success at port:" + port);
            }
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("bind:"+port+" failed", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception
    {
        String ip = "0.0.0.0";
        int port = 9777;
        String path="classpath:spring/applicationContext.xml";
        ApplicationContext ac = new FileSystemXmlApplicationContext(path);
        ServerHandler serverHandler = ac.getBean(ServerHandler.class);
        new GameServer().bind(ip, port, serverHandler);
    }
}