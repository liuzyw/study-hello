package network.programming.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-11-09
 *
 * @author liuzhaoyuan
 */
public class AIOServer {


    private ExecutorService executorService;

    private AsynchronousServerSocketChannel serverSocketChannel;

    public AIOServer() {
        init(10000 + 3);
    }

    public void init(int port) {
        try {
            executorService = Executors.newFixedThreadPool(4);

            serverSocketChannel = AsynchronousServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(port));

            System.out.println("server start ...");

            serverSocketChannel.accept(this, new AIOServerHandler());

            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public static void main(String[] args) {
        new AIOServer();

    }
}

class AIOServerHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServer> {

    @Override
    public void completed(AsynchronousSocketChannel result, AIOServer attachment) {
        attachment.getServerSocketChannel().accept(attachment, this);
        doRead(result);

    }

    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        exc.printStackTrace();

    }

    private void doRead(AsynchronousSocketChannel channel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            /*
             * 异步读操作， read(Buffer destination, A attachment,
             *                    CompletionHandler<Integer, ? super A> handler)
             * destination - 目的地， 是处理客户端传递数据的中转缓存。 可以不使用。
             * attachment - 处理客户端传递数据的对象。 通常使用Buffer处理。
             * handler - 处理逻辑
             */
            channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                /**
                 * 业务逻辑，读取客户端传输数据
                 * attachment - 在completed方法执行的时候，OS已经将客户端请求的数据写入到Buffer中了。
                 *  但是未复位（flip）。 使用前一定要复位。
                 */
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    try {
                        System.out.println(attachment.capacity());
                        // 复位
                        attachment.flip();
                        System.out.println("from client : " + new String(attachment.array(), "UTF-8"));
                        doWrite(channel);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doWrite(AsynchronousSocketChannel result) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.print("enter message send to client > ");
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            buffer.put(line.getBytes("UTF-8"));
            // 重点：必须复位，必须复位，必须复位
            buffer.flip();
            // write方法是一个异步操作。具体实现由OS实现。 可以增加get方法，实现阻塞，等待OS的写操作结束。
            result.write(buffer);
            // result.write(buffer).get(); // 调用get代表服务端线程阻塞，等待写操作完成
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }/* catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}*/
    }
}