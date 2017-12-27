package com.study.util.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2017-12-27
 *
 * @author liuzhaoyuan
 */
public class CPTest {

//    private static Lock lock = new ReentrantLock();
//
//    private static int count = 0;
//    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Store storage = new Store();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            Producer p7 = new Producer(storage);
            // 消费者对象
            executorService.submit(p7);
        }
        for (int i = 0; i < 3; i++) {
            // 消费者对象
            Consumer c1 = new Consumer(storage);
            executorService.submit(c1);
        }
        executorService.shutdown();

    }


}

class Store {

    public int count = 1;
    public List<Integer> list = new ArrayList<>();
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public Condition notFull = lock.newCondition();
    public Condition notEmpty = lock.newCondition();
    public LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public void add() {
        lock.lock();
        try {
//            queue.put(count);  主要是让这个 while 循环能 继续
            while (list.size() > 2) {
                try {
                    notFull.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(count);
            System.out.println("add count" + count);
            count++;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while (list.size() < 1) {
                try {
                    notEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int e = list.get(0);
            list.remove(0);
//            int e = queue.take();
            System.out.println("get e" + e);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

class Consumer extends Thread {

    public Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.get();
        }
    }
}

class Producer extends Thread {

    public Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.add();
        }
    }
}


