package netty.study.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created on 2018-10-13
 *
 * @author liuzhaoyuan
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(port)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new EchoServerHandler());
                    }
                });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("start and listener on: ");

            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(123233).start();
    }

}

class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server received: " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
