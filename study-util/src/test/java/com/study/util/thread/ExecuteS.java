package com.study.util.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-06-20
 *
 * @author liuzhaoyuan
 */
public class ExecuteS {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(8));
        executor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 12; i++) {
            executor.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----");});
        }

        System.out.println(executor);


        try {
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(executor);

executor.shutdown();

    }
}
