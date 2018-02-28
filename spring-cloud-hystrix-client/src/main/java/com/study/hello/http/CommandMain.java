package com.study.hello.http;


/**
 * Created on 2018-02-21
 *
 * @author liuzhaoyuan
 */
public class CommandMain {

    public static void main(String[] args) {
        HystixCommandDemo demo = new HystixCommandDemo();
        String result = demo.execute();

        System.out.println(result);

        ExCommand exCommand = new ExCommand();
        System.out.println(exCommand.execute());
    }
}
