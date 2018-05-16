package com.study.hystrix.http;


import java.util.concurrent.Future;

/**
 * Created on 2018-02-21
 *
 * @author liuzhaoyuan
 */
public class CommandMain {

    public static void main(String[] args) throws Exception {

        HystixCommandDemo demo = new HystixCommandDemo();
        // 同步
        String result = demo.execute();
        System.out.println(result);

        // 一步
        Future<String> future = demo.queue();
        System.out.println(future.get());

        ExCommand exCommand = new ExCommand();
        System.out.println(exCommand.execute());
    }
}
