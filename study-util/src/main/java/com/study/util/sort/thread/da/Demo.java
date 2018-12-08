package com.study.util.sort.thread.da;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created on 2018-12-07
 *
 * <p> 使用原生的 ,对队列进行加锁， 在 wait 的时候必须try 之后必须要 notify </>
 *
 * @author liuzhaoyuan
 */
public class Demo {

    private static LinkedList<Long> queue = new LinkedList<>();

    private static AtomicLong atomicLong = new AtomicLong(1);

    public void put() {
            synchronized (queue) {
                while (queue.size() > 10) {
                    System.out.println("当前队列满");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        System.out.println("------------");
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.addLast(atomicLong.incrementAndGet());
                queue.notify();
            }

    }

    public void take() {

            synchronized (queue) {
                while (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--> " + queue.removeFirst());
                queue.notify();
            }

    }

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

            while (true) {
                put();
            }

        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {

            while (true) {
                take();
            }
        }
    }

}
