package com.study.util.sort.thread.ss;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProceedInsequence {
    private int no = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition con1 = lock.newCondition();
    private final Condition con2 = lock.newCondition();
    private final Condition con3 = lock.newCondition();
    private int curNum = 1;
 
    private void printNum() {
        if (curNum > 75) {
            Thread.currentThread().interrupt();
            return;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + (curNum++));
        }
    }
 
    public void process1() {
        lock.lock();
        try {
            while (no != 1) {
                con1.await();
            }
            printNum();
            no = 2;
            con2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
 
    }
 
    public void process2() {
        lock.lock();
        try {
            while (no != 2) {
                con2.await();
            }
            printNum();
            no = 3;
            con3.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
 
    public void process3() {
        lock.lock();
        try {
            while (no != 3) {
                con3.await();
            }
            printNum();
            no = 1;
            con1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
 
    public static void main(String[] args) {
        final ProceedInsequence p = new ProceedInsequence();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                p.process1();
            }
        }).start();
 
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                p.process2();
            }
        }).start();
 
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                p.process3();
            }
        }).start();
 
    }
}

