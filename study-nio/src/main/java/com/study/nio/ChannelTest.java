package com.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedMap;

/**
 * Created on 2018-03-20
 *
 * @author liuzhaoyuan
 *
 *
 *         一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 *         二、通道的主要实现类 java.nio.channels.Channel 接口：
 *
 *         |--FileChannel
 *
 *         |--SocketChannel
 *
 *         |--ServerSocketChannel
 *
 *         |--DatagramChannel
 *
 *         三、获取通道 1. Java 针对支持通道的类提供了 getChannel() 方法 本地 IO： FileInputStream/FileOutputStream RandomAccessFile
 *
 *         网络IO： Socket ServerSocket DatagramSocket
 *
 *         2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 *
 *         3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 *         四、通道之间的数据传输 transferFrom() transferTo()
 *
 *         五、分散(Scatter)与聚集(Gather) 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 *         六、字符集：Charset 编码：字符串 -> 字节数组 解码：字节数组  -> 字符串
 */
public class ChannelTest {


    public static void main(String[] args) throws IOException {

        System.out.println("----");
//        aaa();

//        bbb();

//        ccc();

//        ddd();

        eee();

    }

    private static void aaa() throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/liuzhaoyuan/Desktop/aa/aaa.txt");

        FileOutputStream outputStream = new FileOutputStream("/Users/liuzhaoyuan/Desktop/aa/aaa_1.txt");

        FileChannel isChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (isChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        System.out.println("done");
        outChannel.close();
        isChannel.close();
        inputStream.close();
        outputStream.close();

    }

    /**
     * 内存映射，直接缓冲区
     */
    private static void bbb() throws IOException {
        FileChannel inChannel = FileChannel
            .open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa.txt"), StandardOpenOption.READ);

        FileChannel outChannel = FileChannel
            .open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa_2.txt"), StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        MappedByteBuffer inMappedByteBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());

        MappedByteBuffer outMappedByteBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(bytes);
        outMappedByteBuffer.put(bytes);

        outChannel.close();
        inChannel.close();


    }

    /**
     * 通道 ，直接缓冲区
     */
    private static void ccc() throws IOException {
        FileChannel inChannel = FileChannel
            .open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa.txt"), StandardOpenOption.READ);

        FileChannel outChannel = FileChannel
            .open(Paths.get("/Users/liuzhaoyuan/Desktop/aa/aaa_3.txt"), StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
    }

    /**
     * 分散和聚集
     */
    private static void ddd() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/liuzhaoyuan/Desktop/aa/aaa.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(512);
        ByteBuffer buffer2 = ByteBuffer.allocate(512);
        ByteBuffer buffer3 = ByteBuffer.allocate(512);

        ByteBuffer[] byteBuffers = {buffer1, buffer2, buffer3};

        fileChannel.read(byteBuffers);

        for (ByteBuffer buffer : byteBuffers) {
            buffer.flip();
        }

        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[1].limit()));

        RandomAccessFile randomAccessFile2 = new RandomAccessFile("/Users/liuzhaoyuan/Desktop/aa/aaa_3.txt", "rw");
        FileChannel fileChannel2 = randomAccessFile2.getChannel();
        fileChannel2.write(byteBuffers);

        randomAccessFile.close();
        randomAccessFile2.close();
        fileChannel.close();
        fileChannel2.close();

    }

    /**
     * 编码解码
     */
    private static void eee() throws IOException {
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        System.out.println(sortedMap);

        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("大蓼扛把子");
        charBuffer.flip();

        ByteBuffer byteBuffer = encoder.encode(charBuffer);
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.flip();
        CharBuffer charBuffer1 = decoder.decode(byteBuffer);

        for (int i = 0; i < charBuffer1.limit(); i++) {
            System.out.println(charBuffer1.get());
        }

    }
}
