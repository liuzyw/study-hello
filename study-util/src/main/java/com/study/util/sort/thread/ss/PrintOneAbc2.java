package com.study.util.sort.thread.ss;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2018-12-07
 * <p>
 * 两个线程打印  1,a,2,b,3,c ......
 * <p>
 * ，。  消息
 *
 * @author liuzhaoyuan
 */
public class PrintOneAbc2 {

    private static int no = 1;

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition num123 = lock.newCondition();

    private static Condition abc = lock.newCondition();

    private static int size = 0;


    private static int numA = 0;

    private static int numB = 0;

    private static char[] chars = new char[]{'a', 'b', 'c'};


    public static void main(String[] args) throws Exception {

        PrintOneAbc2 demo = new PrintOneAbc2();

        for (int i = 0; i < 3; i++) {
            new Thread(demo.new Consumer()).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(demo.new Producer()).start();
        }

    }


    public void put() {
        lock.lock();
        try {
            while (no != 1) {
                try {
                    abc.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(numA++ % 3 + 1  + "   " + Thread.currentThread().getId());

            no = 2;

//            TUt.sel(500);

            num123.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    public void take() {
        lock.lock();
        try {
            while (no != 2) {
                try {
                    num123.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(chars[numB++ % 3]+ "   " + Thread.currentThread().getId());
            no = 1;
//            TUt.sel(400);

            abc.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
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

    public class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                put();

            }
        }
    }
}
