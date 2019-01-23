package com.study.spring.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019-01-12
 *
 * @author liuzhaoyuan
 */
//@Component("wsServer")
public class WSServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WSServer.class);

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {

                    ChannelPipeline pipeline = channel.pipeline();

                    pipeline.addLast(new HttpServerCodec());

                    pipeline.addLast(new ChunkedWriteHandler());

                    pipeline.addLast(new HttpObjectAggregator(4096));

                    pipeline.addLast(new WebSocketServerProtocolHandler("/wsa"));

                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

                    // 心跳机制
                    pipeline.addLast(new IdleStateHandler(2, 4, 10));

                    // 自定义空闲
                    pipeline.addLast(new HeartBeatHandler());

                    pipeline.addLast(new ChatHandler());


                }
            });

        ChannelFuture future = null;
        try {
            future = bootstrap.bind(9999).sync();

            System.out.println("server6 start ....  ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        WSServer wsServer = new WSServer();
        wsServer.start();
    }
}
