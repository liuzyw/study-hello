package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2018-03-22
 *
 * @author liuzhaoyuan
 *
 *         一、使用 NIO 完成网络通信的三个核心：
 *
 *         1. 通道（Channel）：负责连接
 *
 *         java.nio.channels.Channel 接口：
 *
 *         |--SelectableChannel
 *
 *         |--SocketChannel
 *
 *         |--ServerSocketChannel
 *
 *         |--DatagramChannel
 *
 *         |--Pipe.SinkChannel
 *
 *         |--Pipe.SourceChannel
 *
 *         2. 缓冲区（Buffer）：负责数据的存取
 *
 *         3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 */
public class BlockNIOTest {

    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            try {
                server();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(5000);
                client();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private static void server() throws IOException {

        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel
            .open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa_4.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2. 绑定连接
        ssChannel.bind(new InetSocketAddress(9999));

        //3. 获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据，并保存到本地
        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        //发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);

        //6. 关闭通道
        sChannel.close();
        outChannel.close();
        ssChannel.close();
    }

    private static void client() throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa.txt"),
            StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(128);

        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }


        socketChannel.shutdownOutput();

        //接收服务端的反馈
        int len = 0;
        while((len = socketChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        socketChannel.close();
        fileChannel.close();
    }

}
