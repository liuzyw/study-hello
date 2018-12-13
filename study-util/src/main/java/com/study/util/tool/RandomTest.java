package com.study.util.tool;

import java.util.Random;

/**
 * Created on 2018-12-09
 *
 * @author liuzhaoyuan
 */
public class RandomTest {

    public static void main(String[] args) {
        Random random1 = new Random(10000);
        Random random2 = new Random(10000);

        for (int i = 0; i < 10; i++) {
            System.out.println(random1.nextInt());
            System.out.println(random2.nextInt());

        }

        new Thread(() -> {
            int a = 10 / 3;
        }).start();
        new Thread(() -> {

            System.out.println("--");
        }).start();

        while (true) {
            ;
        }

    }

}
