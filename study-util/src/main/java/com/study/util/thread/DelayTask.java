package com.study.util.thread;

import java.util.Objects;
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
     * 间隔时间
     */
    private long intervalTime;

    /**
     * 最大循环次数
     */
    private int maxCount;


    /**
     * 队列中最大执行次数
     */
    public static final int DEFAULT_MAX_TIME = 5;

    // 默认是 10s
    public static final int DEFAULT_INTERVAL_TIME = 10000;

    public DelayTask() {
        this.id = 1L;
        this.timeout = System.currentTimeMillis() + 1000;
        this.task = null;
        this.count = 0;
        this.maxCount = DEFAULT_MAX_TIME;
        this.intervalTime = DEFAULT_INTERVAL_TIME;
    }

    public DelayTask(long id, long timeout, int maxCount, int intervalTime, T task) {
        this.id = id;
        this.timeout = System.currentTimeMillis() + timeout;
        this.task = task;
        this.count = 0;
        this.maxCount = maxCount;
        this.intervalTime = intervalTime;
    }

    public DelayTask(long id, T task, DelayEnum delayEnum) {
        this.id = id;
        this.task = task;
        this.timeout = System.currentTimeMillis() + delayEnum.time;
        this.maxCount = delayEnum.count;
        this.intervalTime = delayEnum.time;
        this.count = 0;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = System.currentTimeMillis() + timeout;
    }

    public T getTask() {
        return this.task;
    }


    public void setTask(T task) {
        this.task = task;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }


    public void setNextTask() {
        this.setCount(count + 1);
        this.setTimeout(intervalTime);
    }


    @Override
    public String toString() {
        return "DelayTask{" +
            "id=" + id +
            ", timeout=" + timeout +
            ", count=" + count +
            ", intervalTime=" + intervalTime +
            ", maxCount=" + maxCount +
            '}';
    }


    /**
     * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof DelayTask) {
            DelayTask x = (DelayTask) other;
            long diff = this.timeout - x.getTimeout();
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            } else {
                return 0;
            }
        }
        long d = (getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DelayTask)) {
            return false;
        }
        DelayTask<?> delayTask = (DelayTask<?>) o;
        return getId() == delayTask.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
