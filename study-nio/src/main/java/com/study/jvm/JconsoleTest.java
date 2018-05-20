package com.study.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-05-20
 *
 * @author liuzhaoyuan
 */
public class JconsoleTest {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        new Thread(() -> {
            int i = 0;
            List<Integer> list = new ArrayList<>();
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] bytes = new byte[1024];
                list.add(i);
                System.out.println(i++);
            }
        }, "test1").start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] bytes = new byte[1024];
                System.out.println(i++);
            }
        }, "test2").start();

    }
}
