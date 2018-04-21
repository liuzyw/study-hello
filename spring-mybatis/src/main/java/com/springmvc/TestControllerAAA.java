package com.springmvc;

import com.springmvc.annotation.MyController;
import com.springmvc.annotation.MyRequestMapping;
import com.springmvc.annotation.MyRequestParam;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
@MyRequestMapping("/test")
public class TestControllerAAA {

    @MyRequestMapping("/aaa")
    public void test1(HttpServletRequest request, HttpServletResponse response,
        @MyRequestParam("param") String param) {
        System.out.println(param);
        try {
            response.getWriter().write("doTest method success! param: " + param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @MyRequestMapping("/doTest2")
    public void test2(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("doTest2 method success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}