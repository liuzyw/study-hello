package com.study.util.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2017-12-26
 *
 * @author liuzhaoyuan
 */
public class FuterTest {

    public static void main(String[] args) throws Exception{
        aaa();
    }

    public static void aaa() throws Exception{
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 200, TimeUnit.SECONDS,
//            new LinkedBlockingDeque<>(200));
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            final int j = i;
            FutureTask<Integer> futureTask = new FutureTask(new Callable() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000);

                    return j;
                }
            });
            futures.add(futureTask);
            executor.submit(futureTask);
        }
        int num = 0;
        for (int i = 0; i < futures.size(); i++) {
            try {
                num += futures.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        FutureTask<Integer> futureTask1 = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(100);

                return 33;
            }
        });
        executor.submit(futureTask1);

        FutureTask<Integer> futureTask2 = new FutureTask(new Callable() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);

                return 22;
            }
        });
        executor.submit(futureTask2);

        System.out.println(futureTask1.get() + futureTask2.get());

        System.out.println(num);
        executor.shutdown();
    }


}
