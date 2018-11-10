package network.programming.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created on 2018-11-09
 *
 * @author liuzhaoyuan
 */
public class NIOServer {

    public static void main(String[] args) {
        new NIOServer(10004).start();
    }

    private Selector selector;

    private int port;

    public NIOServer(int port) {
        this.port = port;
        init();
    }

    private void init() {

        try {

            System.out.println(" server starting ... ");

            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println(" server start done ...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                selector.select();

                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {

                    SelectionKey key = keyIterator.next();

                    keyIterator.remove();

                    try {
                        if (key.isValid()) {

                            if (key.isAcceptable()) {
                                accept(key);
                            }
                            if (key.isReadable()) {
                                read(key);
                            }

                            if (key.isWritable()) {
                                write(key);
                            }
                        }
                    } catch (Exception ex) {

                    }

                }
            }


        } catch (Exception e) {

        }
    }

    private void accept(SelectionKey key) {

        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void read(SelectionKey key) {

        try {
            ByteBuffer buffer = ByteBuffer.allocate(512);

            SocketChannel channel = (SocketChannel) key.channel();
            channel.read(buffer);

            buffer.flip();

            byte[] bytes = new byte[buffer.remaining()];

            buffer.get(bytes);

            System.out.println("receive from client : " + new String(bytes, "UTF-8"));

            buffer.clear();

            channel.register(selector, SelectionKey.OP_WRITE);

        } catch (Exception e) {
            try {
                key.channel().close();
                key.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();

        }

    }

    private void write(SelectionKey key) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(512);

            SocketChannel channel = (SocketChannel) key.channel();

            Scanner scanner = new Scanner(System.in);

            System.out.print("input msg to client -->  ");

            String line = scanner.nextLine();

            buffer.put(line.getBytes("UTF-8"));

            buffer.flip();

            channel.write(buffer);

            channel.register(selector, SelectionKey.OP_READ);


        } catch (Exception e) {

            try {
                key.channel().close();
                key.cancel();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
