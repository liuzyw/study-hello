package com.study.util.sort.thread.dc;

import com.study.util.sort.thread.TUt;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2018-12-07
 *
 * @author liuzhaoyuan
 */
public class Demo {

    private static LinkedList<Long> queue = new LinkedList<>();

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition notFull = lock.newCondition();

    private static Condition notEmpty = lock.newCondition();

    private static int size = 10;

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


    public void put() {
        lock.lock();
        try {
            while (queue.size() > size) {
                try {
                    System.out.println("---->  full");
                    notFull.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            queue.addLast(atomicLong.incrementAndGet());
            TUt.sel(500);

            notEmpty.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }


    public void take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                try {
                    System.out.println("---->  empty");
                    notEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("---> " + queue.removeFirst());
            TUt.sel(400);

            notFull.signal();

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









