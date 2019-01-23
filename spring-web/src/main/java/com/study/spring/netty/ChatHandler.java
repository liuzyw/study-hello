package com.study.spring.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.io.Serializable;
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

        Channel incoming = ctx.channel();
        for (Channel channel : users) {
            if (channel != incoming) {
                channel.writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.text()));
            } else {
                channel.writeAndFlush(new TextWebSocketFrame("[you]" + msg.text()));
            }
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asLongText();
        LOGGER.info("客户端 " + ctx.channel().remoteAddress() + " 加入 channelId 为:" + channelId);

        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        users.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));

        users.add(incoming);

        System.out.println("Client:" + incoming.remoteAddress() + "加入");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asLongText();
        LOGGER.info("客户端 " + ctx.channel().remoteAddress() + " 被移除 channelId 为:" + channelId);

        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel

        Channel incoming = ctx.channel();

        users.remove(incoming);

        // Broadcast a message to multiple Channels
        users.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));

        System.out.println("Client:" + incoming.remoteAddress() + "离开");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        LOGGER.error("客户端 " + ctx.channel().remoteAddress() + " 异常 ", cause);

        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
