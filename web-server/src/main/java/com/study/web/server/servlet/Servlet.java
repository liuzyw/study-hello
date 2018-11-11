package com.study.web.server.servlet;

import com.study.web.server.Request;
import com.study.web.server.Response;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public interface Servlet {


    void service(Request request, Response response);

    void doGet(Request request, Response response);

    void doPost(Request request, Response response);

}
