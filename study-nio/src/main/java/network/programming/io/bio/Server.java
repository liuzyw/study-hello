package network.programming.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 2018-11-06
 *
 * @author liuzhaoyuan
 */
public class Server {

    public static void main(String[] args) {
        int port = 1 + 10000;

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("server start... ");

            while (true) {
                Socket socket = server.accept();

                // 一个请求一个线程
                // 也可以改为线程池
                new Thread(new BIOHandler(socket)).start();
            }

        } catch (Exception e) {

        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

class BIOHandler implements Runnable {


    private Socket socket;

    public BIOHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter printWriter = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            String readMsg = null;

            while (true) {
                System.out.println("server reading ...");

                if ((readMsg = reader.readLine()) == null) {
                    break;
                }

                System.out.println(readMsg);
                printWriter.println("server receive: " + readMsg);
                printWriter.flush();

            }


        } catch (Exception e) {

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    reader.close();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
