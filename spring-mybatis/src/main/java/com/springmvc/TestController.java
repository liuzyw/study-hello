package com.springmvc;

import com.springmvc.annotation.MyAutowrited;
import com.springmvc.annotation.MyController;
import com.springmvc.annotation.MyRequestMapping;
import com.springmvc.annotation.MyRequestParam;
import com.springmvc.service.HelloService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
public class TestController {

    @MyAutowrited
    private HelloService helloService;

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

    @MyRequestMapping("/bbb")
    public void test1(HttpServletRequest request, HttpServletResponse response) {
        try {
            helloService.sayHello();
            response.getWriter().write("doTest method hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/")
    public void testd1(HttpServletResponse response) {
        System.out.println("index");
        try {
            response.getWriter().write("hello my ssm index");
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