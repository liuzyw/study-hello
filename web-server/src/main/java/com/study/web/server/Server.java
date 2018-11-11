package com.study.web.server;

import com.study.web.server.servlet.Dispatcher;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class Server {

    private ServerSocket serverSocket;

    private volatile boolean isRunning = false;


    public static void main(String[] args) {

        Server server = new Server();

        server.start();
    }


    public void start() {
        try {
            isRunning = true;
            serverSocket = new ServerSocket(10007);

            while (isRunning) {
                try {
                    receive();

                } catch (Exception e) {
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void receive() {

        try {
            Socket socket = serverSocket.accept();
            System.out.println("---- > one client ");

            new Thread(new Dispatcher(socket)).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }


}
