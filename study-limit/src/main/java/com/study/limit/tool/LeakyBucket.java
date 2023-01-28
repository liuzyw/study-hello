package com.study.limit.tool;


import java.util.concurrent.locks.ReentrantLock;

//   漏桶算法
//   漏桶具有固定容量，出水速率是固定常量（流出请求）
//        如果桶是空的，则不需流出水滴
//        可以以任意速率流入水滴到漏桶（流入请求）
//        如果流入水滴超出了桶的容量，则流入的水滴溢出（新请求被拒绝）
//        漏桶限制的是常量流出速率（即流出速率是一个固定常量值），所以最大的速率就是出水的速率，不能出现突发流量。
public class LeakyBucket {
    private int rate;  // 每秒出水多少
    private long capacity; // 桶大小
    private long size; // 当前多少水
    private long lastLeakMs; // 上次出水时间

    private ReentrantLock lock;

    public LeakyBucket() {
        this(2, 10);
    }

    public LeakyBucket(int rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.size = 0;
        this.lastLeakMs = System.currentTimeMillis();
        this.lock = new ReentrantLock();
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLastLeakMs() {
        return lastLeakMs;
    }

    public void setLastLeakMs(long lastLeakMs) {
        this.lastLeakMs = lastLeakMs;
    }

    public boolean allow(int num) {

        lock.lock();
        try {
            long now = System.currentTimeMillis();
            long eclipse = ((now - lastLeakMs) / 1000) * rate;
            System.out.println("eclipse:" + eclipse);

            if (size <= eclipse) {
                size = num;
                System.out.println("temp:" + 0 + "桶大小:" + size);

            } else {
                long temp = (size - eclipse) + num;
                if (temp > capacity) {
                    System.out.println("temp:" + temp + "不够了，桶:" + size);
                    return false;
                }
                size = temp;
                System.out.println("temp:" + temp + "桶大小:" + size);
            }
            lastLeakMs = now;
            return true;

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucket leakyBucket = new LeakyBucket(2, 10);
        System.out.println(leakyBucket.allow(10));
        System.out.println("-------------------");

        System.out.println(leakyBucket.allow(2));
        System.out.println("-------------------");

        Thread.sleep(1500);
        System.out.println(leakyBucket.allow(10));
        System.out.println("-------------------");

        System.out.println(leakyBucket.allow(2));
        System.out.println("-------------------");

        System.out.println(leakyBucket.allow(10));
        System.out.println("-------------------");

        System.out.println(leakyBucket.allow(8));
        System.out.println("-------------------");



    }


}
