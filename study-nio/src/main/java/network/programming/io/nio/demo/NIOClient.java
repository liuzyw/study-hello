package network.programming.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created on 2018-11-09
 *
 * @author liuzhaoyuan
 */
public class NIOClient {


    public static void main(String[] args) {
        new NIOClient("localhost", 10004).start();
    }

    private String host;
    private int port;

    private SocketChannel socketChannel;

    public NIOClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(host, port));

            System.out.println(" client start done ...");
        } catch (Exception e) {

        }
    }

    public void start() {

        try {

            ByteBuffer buffer = ByteBuffer.allocate(512);

            while (true) {

                System.out.print("input msg to server -->  ");

                Scanner scanner = new Scanner(System.in);

                String line = scanner.nextLine();

                buffer.put(line.getBytes("UTF-8"));

                buffer.flip();

                socketChannel.write(buffer);

                buffer.clear();

                socketChannel.read(buffer);
                buffer.flip();

                byte[] bytes = new byte[buffer.remaining()];

                buffer.get(bytes);
                System.out.println(" receive from server : " + new String(bytes, "UTF-8"));

                buffer.clear();

            }


        } catch (Exception e1) {

            System.out.println("--- >");
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            e1.printStackTrace();
        }
    }
}
