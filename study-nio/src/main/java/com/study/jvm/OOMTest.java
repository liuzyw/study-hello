package com.study.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */
public class OOMTest {

    /**
     * 模拟内存溢出
     */
    public static void main(String[] args) throws Exception {

        TimeUnit.SECONDS.sleep(20);

        List<User> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            byte[] bytes = new byte[1024 * 10];
            list.add(new User());
        }
    }

}
