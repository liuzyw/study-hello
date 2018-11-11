package com.study.web.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class Response {

    private PrintWriter printWriter;

    private StringBuilder content;

    private StringBuilder headInfo;


    private int len;


    private String blank = " ";

    private String CRLF = "\r\n";

    public Response() {
        content = new StringBuilder();
        headInfo = new StringBuilder();
        len = 0;
    }

    public Response(Socket socket) {
        this();

        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response(OutputStream outputStream) {
        this();

        try {
            printWriter = new PrintWriter(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createHeadInfo(int code) {

        headInfo.append("HTTP/1.1").append(blank);
        headInfo.append(code).append(blank);

        switch (code) {
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("Not Found").append(CRLF);
                break;
            case 500:
                headInfo.append("Server Error").append(CRLF);
                break;

        }

        headInfo.append("Date: " + new Date()).append(CRLF);
        headInfo.append("Server:Apache").append(CRLF);
        headInfo.append("Content-Type:text/html;charset=UTF-8").append(CRLF);
        headInfo.append("Content-Length:" + len).append(CRLF).append(CRLF);

    }

    public Response print(String info) {
        content.append(info);
        len += info.getBytes().length;
        return this;
    }

    public Response println(String info) {
        content.append(info).append(CRLF);
        len += (info + CRLF).getBytes().length;
        return this;
    }


    public void push(int code) {
        createHeadInfo(code);

        printWriter.append(headInfo);
        printWriter.append(content);
        printWriter.flush();
    }

}
