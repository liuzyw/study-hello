package com.study.nio;

import java.nio.ByteBuffer;

/**
 * Created on 2018-03-20
 *
 * @author liuzhaoyuan
 *
 *         一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 *         根据数据类型不同（boolean 除外），提供了相应类型的缓冲区： ByteBuffer CharBuffer ShortBuffer IntBuffer LongBuffer FloatBuffer
 *         DoubleBuffer
 *
 *         上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 *         二、缓冲区存取数据的两个核心方法： put() : 存入数据到缓冲区中 get() : 获取缓冲区中的数据
 *
 *         三、缓冲区中的四个核心属性： capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 *
 *         limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写） position : 位置，表示缓冲区中正在操作数据的位置。
 *
 *         mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 *
 *         0 <= mark <= position <= limit <= capacity
 *
 *         四、直接缓冲区与非直接缓冲区： 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中 直接缓冲区：通过 allocateDirect()
 *         方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
public class BufferTest {

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(128);
    private static ByteBuffer byteBufferDirect = ByteBuffer.allocateDirect(128);


    public static void main(String[] args) {

        byteBuffer();
        byteBuffer1();

    }


    private static void byteBuffer() {

        print();
        // 写模式
        for (int i = 0; i < 5; i++) {
            byteBuffer.put((byte) (i + 66));
        }

        byteBuffer.put("abc".getBytes());
        print();

        // 读模式
        byteBuffer.flip();
        print();
        // 不改变指针
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print(byteBuffer.get(i));
        }
        System.out.println();
        print();

        // 改变指针
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        print();
        System.out.println(new String(bytes, 0, bytes.length));

        // 可重复读
        byteBuffer.rewind();
        print();

        // 指针重置， 但是数据还在
        byteBuffer.clear();

        byteBuffer.put("byteBuffer".getBytes());
        print();
        byteBuffer.flip();
        byte[] bytes1 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes1, 0, 3);
        System.out.println(new String(bytes1, 0, 3));
        print();
        byteBuffer.mark();

        byteBuffer.get(bytes1, 3, 3);
        System.out.println(new String(bytes1, 3, 3));
        print();

        byteBuffer.reset();
        print();

    }

    private static void byteBuffer1() {
        System.out.println(byteBufferDirect.isDirect());
    }

    private static void print() {
        System.out.println("position: " + byteBuffer.position());
        System.out.println("limit: " + byteBuffer.limit());
        System.out.println("capacity: " + byteBuffer.capacity());

        System.out.println("------------------------");
        System.out.println();
    }
}
