package com.study.util.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2017-10-20
 *
 * @author liuzhaoyuan
 */
public class ThreadUtils {

    private ThreadUtils() {
    }

    public static ExecutorService getDefaultThreadPoolExecutor() {
        return new ThreadPoolExecutor(3, 6, 200, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100));
    }

}
