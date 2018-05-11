package com.study.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */
public class OOMTest {

    /**
     * 模拟内存溢出
     */
    public static void main(String[] args) {

        List<User> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new User());
        }
    }

}
