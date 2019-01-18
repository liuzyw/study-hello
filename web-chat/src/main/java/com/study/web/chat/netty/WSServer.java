package com.study.web.chat.netty;

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
import io.netty.handler.stream.ChunkedWriteHandler;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019-01-12
 *
 * @author liuzhaoyuan
 */
public class WSServer implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WSServer.class);

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ChannelPipeline pipeline = ch.pipeline();

                        // http 编解码器
                        pipeline.addLast(new HttpServerCodec());

                        // 对大量数据写的 support for writing a large data stream
                        pipeline.addLast(new ChunkedWriteHandler());

                        // 聚合器 消息的最大长度
                        pipeline.addLast(new HttpObjectAggregator(4096 * 32));

                        // netty 的支持
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

                        // 我们自己的 handler
                        pipeline.addLast(new ChatHandler());

                    }
                });

            ChannelFuture future = bootstrap.bind(9999).sync();

            LOGGER.info("web chat server start bind 9999 ");

            future.channel().closeFuture().sync();

        } catch (Exception e) {

            LOGGER.error("WS server error ", e);

        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }


    }


}
