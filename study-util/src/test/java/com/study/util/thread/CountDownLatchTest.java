package com.study.util.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 2017-11-28
 *
 * @author liuzhaoyuan
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 200, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200));

        AtomicInteger atomicInteger = new AtomicInteger();
        final CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    atomicInteger.addAndGet(1);
                    latch.countDown();
                }
            });
        }
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- ------ " + atomicInteger.get());
    }

}
