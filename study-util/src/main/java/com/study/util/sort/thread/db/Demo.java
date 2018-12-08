package com.study.util.sort.thread.db;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 2018-12-07
 *
 * <p>使用阻塞队列， 因为队列是加锁了的， 所以多线程访问没有问题</>
 *
 * @author liuzhaoyuan
 */
public class Demo {

    private static LinkedBlockingDeque<Long> queue = new LinkedBlockingDeque<>();

    private static AtomicLong atomicLong = new AtomicLong(1);


    public static void main(String[] args) throws Exception {

        Demo demo = new Demo();

        for (int i = 0; i < 5; i++) {
            new Thread(demo.new Consumer()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(demo.new Producer()).start();
        }

    }


    public class Producer implements Runnable {

        @Override
        public void run() {

            try {
                while (true) {
                    TimeUnit.MILLISECONDS.sleep(1000);

                    queue.put(atomicLong.incrementAndGet());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {

            try {
                while (true) {
                    TimeUnit.MILLISECONDS.sleep(500);

                    long var = queue.take();

                    System.out.println("Consumer :  " + var);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
