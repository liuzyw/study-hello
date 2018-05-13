package com.study.data.table.idgenerator.snowflake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws Exception {
/** case1: 测试每秒生产id个数?
 *   结论: 每秒生产id个数300w+ */
//        testPerSecondProductIdNums();

        /** case2: 单线程-测试多个生产者同时生产N个id,验证id是否有重复?
         *   结论: 验证通过,没有重复. */
//        testProductId(1,2,10000);//验证通过!
        //testProductId(1,2,20000);//验证通过!

        /** case3: 多线程-测试多个生产者同时生产N个id, 全部id在全局范围内是否会重复?
         *   结论: 验证通过,没有重复. */
        testProductIdByMoreThread(1, 2, 100000);//单机测试此场景,性能损失至少折半!

    }

    public static void testProductId(int dataCenterId, int workerId, int n) {
        IdWorker idWorker = new IdWorker(workerId, dataCenterId);
        IdWorker idWorker2 = new IdWorker(workerId + 1, dataCenterId);
        Set<Long> setOne = new HashSet<>();
        Set<Long> setTow = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            setOne.add(idWorker.nextId());//加入set
        }
        long end1 = System.currentTimeMillis() - start;
        log.info("第一批ID预计生成{}个,实际生成{}个<<<<*>>>>共耗时:{}", n, setOne.size(), end1);

        for (int i = 0; i < n; i++) {
            setTow.add(idWorker2.nextId());//加入set
        }
        long end2 = System.currentTimeMillis() - start;
        log.info("第二批ID预计生成{}个,实际生成{}个<<<<*>>>>共耗时:{}", n, setTow.size(), end2);

        setOne.addAll(setTow);
        log.info("合并总计生成ID个数:{}", setOne.size());

    }

    public static void testPerSecondProductIdNums() {
        IdWorker idWorker = new IdWorker(1, 2);
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; System.currentTimeMillis() - start < 1000; i++, count = i) {
            /**  测试方法一: 此用法纯粹的生产ID,每秒生产ID个数为300w+ */
            idWorker.nextId();
            /**  测试方法二: 在log中打印,同时获取ID,此用法生产ID的能力受限于log.error()的吞吐能力.
             * 每秒徘徊在10万左右. */
            //log.error("{}",idWorker.nextId());
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        System.out.println(count);
    }

    public static void testProductIdByMoreThread(int dataCenterId, int workerId, int n) throws InterruptedException {
        List<Thread> tlist = new ArrayList<>();
        Set<Long> setAll = new HashSet<>();
        CountDownLatch cdLatch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        int threadNo = dataCenterId;
        Map<String, IdWorker> idFactories = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            //用线程名称做map key.
            idFactories.put("snowflake" + i, new IdWorker(workerId, threadNo++));
        }
        for (int i = 0; i < 10; i++) {
            Thread temp = new Thread(() -> {
                Set<Long> setId = new HashSet<>();
                IdWorker idWorker = idFactories.get(Thread.currentThread().getName());
                for (int j = 0; j < n; j++) {
                    setId.add(idWorker.nextId());
                }
                synchronized (setAll) {
                    setAll.addAll(setId);
                    log.info("{}生产了{}个id,并成功加入到setAll中.", Thread.currentThread().getName(), n);
                }
                cdLatch.countDown();
            }, "snowflake" + i);
            tlist.add(temp);
        }
        for (int j = 0; j < 10; j++) {
            tlist.get(j).start();
        }
        cdLatch.await();

        long end1 = System.currentTimeMillis() - start;

        log.info("共耗时:{}毫秒,预期应该生产{}个id, 实际合并总计生成ID个数:{}", end1, 10 * n, setAll.size());

    }
}
