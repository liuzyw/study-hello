package com.study.spring.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import java.io.Serializable;

/**
 * Created on 2019-01-19
 *
 * @author liuzhaoyuan
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter implements Serializable {

    private static final Long serialVersionUID = 1L;


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {

            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            IdleState state = idleStateEvent.state();

            if (state == IdleState.READER_IDLE) {
                System.out.println(ctx.channel().id() + "  进入读空闲 ..");
            } else if (state == IdleState.WRITER_IDLE) {
                System.out.println(ctx.channel().id() + "  进入写空闲 ..");
            } else if (state == IdleState.ALL_IDLE) {

                Channel channel = ctx.channel();

                System.out.println(ctx.channel().id() + "  准备关闭 ..");

                // 这一步会关闭
//                channel.close();

            }


        }

    }
}
