package network.programming.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created on 2018-11-06
 *
 * @author liuzhaoyuan
 */
public class Client {

    public static void main(String[] args) {
        int port = 1 + 10000;
        String host = "localhost";

        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket(host, port);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            writer = new PrintWriter(socket.getOutputStream(), true);

            String msg = null;

            while (true) {
                msg = scanner.nextLine();

                if ("end".equals(msg)) {
                    break;
                }

                writer.println(msg);
                writer.flush();

                System.out.println(reader.readLine());

            }

        } catch (Exception e) {

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
