package com.study.web.server.servlet;

import com.study.web.server.Request;
import com.study.web.server.Response;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class RegisterServlet implements Servlet {


    @Override
    public void service(Request request, Response response) {

        System.out.println("register service ....");

    }

    @Override
    public void doGet(Request request, Response response) {

    }

    @Override
    public void doPost(Request request, Response response) {

    }
}
