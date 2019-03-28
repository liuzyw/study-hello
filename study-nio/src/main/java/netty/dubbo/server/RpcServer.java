package netty.dubbo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public class RpcServer {

    private RegistCenter registCenter;

    private String serviceAddress;

    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(RegistCenter registCenter, String serviceAddress) {
        this.registCenter = registCenter;
        this.serviceAddress = serviceAddress;
    }

    public void publisher() {

        for (String serviceName : handlerMap.keySet()) {
            registCenter.register(serviceName, serviceAddress);
        }

        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
//                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 4, 1000));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("encoder", new ObjectEncoder());
//                    pipeline.addLast("decoder", new ObjectDecoder(, 1024, ClassResolvers.cacheDisabled()));
                    pipeline.addLast(new RpcServerHandler(handlerMap));
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class RpcServerHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
}