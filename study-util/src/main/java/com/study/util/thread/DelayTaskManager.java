package com.study.util.thread;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 延迟队列管理任务
 */
public class DelayTaskManager {

    /**
     * 创建一个最初为空的新 DelayQueue
     */
    private static final DelayQueue<DelayTask> queue = new DelayQueue<>();

    // 队列最大小
    private static final long MAX_QUEUE_SIZE = 1000L;

    // 队列任务自增序列号
    private static final AtomicLong atomic = new AtomicLong(0);


    private static ExecutorService executor = new ThreadPoolExecutor(2, 2, 200L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>());

    public DelayTaskManager() {
    }

    /**
     * 守护线程
     */
    private Thread daemonThread;

    /**
     * 初始化守护线程
     */
    public void init() {
        daemonThread = new Thread(() -> {
            while (true) {
                try {
                    //从延迟队列中取值,如果没有对象过期则队列一直等待，
                    final DelayTask t1 = queue.take();
                    if (t1 != null) {
                        //修改问题的状态
                        final Consumer<Boolean> task = t1.getTask();
                        if (task == null) {
                            continue;
                        }
                        System.out.println("[start task id: " + t1.getId() + "]");
                        executor.submit(() -> {
                            try {
                                Boolean result = task.consume();
                                if (!result) {
                                    // 重新调度
                                    if (t1.getCount() < t1.getMaxCount()) {
                                        t1.setNextTask();
                                        queue.put(t1);
                                    } else {
                                        System.out.println("多次调度也不成功 task no:" + t1.getId());
                                        task.fallback();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }


    /**
     * 加锁判断 是否达到 最大
     * 添加任务，
     * time 延迟时间
     * task 任务
     * 用户为问题设置延迟时间
     */
    public synchronized long put(Consumer<Boolean> task, DelayEnum delayEnum) {

        if (queue.size() > MAX_QUEUE_SIZE) {
            System.out.println("refund queue is too big, queue size:" + queue.size());
            return 0L;
        }

        long no = atomic.incrementAndGet();
        //创建一个任务
        DelayTask k = new DelayTask(no, task, delayEnum);
        //将任务放在延迟的队列中
        System.out.println("put task into queue, task:" + k);
        queue.put(k);
        return no;
    }
}
