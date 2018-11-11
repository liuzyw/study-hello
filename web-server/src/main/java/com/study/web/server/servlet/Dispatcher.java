package com.study.web.server.servlet;

import com.study.web.server.Request;
import com.study.web.server.Response;
import com.study.web.server.WebApp;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created on 2018-11-11
 *
 * @author liuzhaoyuan
 */
public class Dispatcher implements Runnable {

    private Socket socket;

    private Request request;

    private Response response;


    public Dispatcher(Socket socket) {
        this.socket = socket;
        request = new Request(socket);
        response = new Response(socket);
    }

    @Override
    public void run() {

        try {
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl().trim());
            if (servlet == null) {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("404.html");
                byte[] bytes = new byte[1024 * 1024];
                int len = is.read(bytes);

                response.print(new String(bytes, 0, len));
                response.push(404);
                return;

            } else {
                servlet.service(request, response);

                response.push(200);
            }
        } catch (Exception e) {
            response.push(500);

            e.printStackTrace();
        } finally {
            release();
        }

    }

    //释放资源
    private void release() {
        try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
