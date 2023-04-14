package com.study.limit.tool;


import java.util.concurrent.locks.ReentrantLock;

//令牌桶有以下特点：
//
//        令牌按固定的速率被放入令牌桶中
//
//        桶中最多存放 B 个令牌，当桶满时，新添加的令牌被丢弃或拒绝
//
//        如果桶中的令牌不足 N 个，则不会删除令牌，且请求将被限流（丢弃或阻塞等待）
//
//        令牌桶限制的是平均流入速率（允许突发请求，只要有令牌就可以处理，支持一次拿3个令牌，4个令牌...），并允许一定程度突发流量，所以也是非常常用的限流算法。
public class TokenBucket {
    private int rate;  // 每秒出水多少
    private long capacity; // 桶大小
    private long size; // 当前多少水
    private long lastLeakMs; // 上次出水时间

    private ReentrantLock lock;

    public TokenBucket() {
        this(2, 10);
    }

    public TokenBucket(int rate, int capacity) {
        this.rate = rate;
        this.capacity = capacity;
        this.size = capacity;
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

    public boolean allow(long num) {

        lock.lock();
        try {
            long now = System.currentTimeMillis();
            long eclipse = ((now - lastLeakMs) / 1000) * rate;
            System.out.println("eclipse:" + eclipse + "桶大小:" + size);
            long temp = Math.min((size + eclipse), capacity);
            if (temp >= num) {
                size = temp - num;
                System.out.println("temp:" + temp + "桶大小:" + size);
            } else {
                System.out.println("temp:" + 0 + "不够了，桶:" + size);
                return false;
            }
            lastLeakMs = now;
            return true;

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket leakyBucket = new TokenBucket(2, 10);
        System.out.println(leakyBucket.allow(10));
        System.out.println("--------------------");

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
