package com.study.web.server.servlet;

import com.study.web.server.Request;
import com.study.web.server.Response;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class LoginServlet implements Servlet {


    @Override
    public void service(Request request, Response response) {

        System.out.println("login service ....");

        response.print("<html>");
        response.print("<head>");

        response.print("<title>");
        response.print("success");
        response.print("</title>");
        response.print("</head>");

        response.print("<body>");

        response.print("登录成功");
        response.print("</body>");
        response.print("</html>");


    }

    @Override
    public void doGet(Request request, Response response) {

    }

    @Override
    public void doPost(Request request, Response response) {

    }
}
