package netty.study.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;

/**
 * Created on 2018-10-13
 *
 * @author liuzhaoyuan
 */
public class EchoClient {

    private String host;

    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new EchoClientHandler());
                    }
                });

            ChannelFuture future = bootstrap.bind().sync();

            future.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost", 123233).start();
    }
}

class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received: " + ByteBufUtil.hexDump(byteBuf.readBytes(byteBuf.readableBytes())));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
