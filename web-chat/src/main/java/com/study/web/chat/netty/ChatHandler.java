package com.study.web.chat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.io.Serializable;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019-01-12
 *
 * @author liuzhaoyuan
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements Serializable {

    private static final Long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatHandler.class);

    // 用于记录和管理所有客户端
    private static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String text = msg.text();
        LOGGER.info("receive msg: " + text);

        users.forEach(channel -> {

            channel.writeAndFlush(new TextWebSocketFrame("server receive msg: " + LocalDateTime.now() + " " + text));

        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asLongText();
        LOGGER.info("客户端 " + ctx.channel().remoteAddress() + " 加入 channelId 为:" + channelId);

        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asLongText();
        LOGGER.info("客户端 " + ctx.channel().remoteAddress() + " 被移除 channelId 为:" + channelId);

        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        LOGGER.error("客户端 " + ctx.channel().remoteAddress() + " 异常 ", cause);

        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
