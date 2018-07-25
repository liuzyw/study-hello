package com.study.util.thread;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列任务
 *
 * @param <T>
 */
public class DelayTask<T extends Consumer<Boolean>> implements Delayed {

    private long id;

    /**
     * 到期时间
     */
    private long timeout;

    /**
     * 任务对象
     */
    private T task;

    /**
     * 循环次数
     */
    private int count;


    /**
     * 队列中最大执行次数
     */
    public static final int MAX_TIME = 5;

    // 默认是 10s
    public static final int TIME_OUT = 10000;


    public DelayTask (long id, long timeout, T task) {
        this.id = id;
        this.timeout = System.currentTimeMillis() + timeout;
        this.task = task;
        this.count = 0;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public long getTimeout () {
        return timeout;
    }

    public void setTimeout (long timeout) {
        this.timeout = timeout;
    }


    public int getCount () {
        return count;
    }

    @Override
    public String toString () {
        return "DelayTask{" +
                "id=" + id +
                ", timeout=" + timeout +
                ", task=" + task +
                ", count=" + count +
                '}';
    }

    public void setCount (int count) {
        this.count = count;
    }

    /**
     * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
     */
    @Override
    public long getDelay (TimeUnit unit) {
        return unit.convert(this.timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo (Delayed other) {
        if (other == this)
            return 0;
        if (other instanceof DelayTask) {
            Task x = (Task) other;
            long diff = this.timeout - x.getTimeout();
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 0;
        }
        long d = (getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    public T getTask () {
        return this.task;
    }

    @Override
    public int hashCode () {
        return task.hashCode();
    }

    @Override
    public boolean equals (Object object) {
        if (object instanceof Task) {
            return object.hashCode() == hashCode() ? true : false;
        }
        return false;
    }


}
