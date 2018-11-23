package com.study.redis;

import com.study.util.tool.RandomUtil;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import redis.clients.jedis.Jedis;

/**
 * Created on 2018-11-23
 *
 * @author liuzhaoyuan
 */
public class TestLock {

    private static int num = 1;


    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("连接成功");
        System.out.println("服务正在运行: " + jedis.ping());

        LockU lockU = new LockU(jedis);

        boolean tryLock = lockU.tryLock("aaa", "dd", 20000);

        System.out.println(tryLock);

        boolean tryLock2 = lockU.tryLock("aaa", "dddd", 20000);
        System.out.println(tryLock2);

//        TimeUnit.SECONDS.sleep(10);

        boolean unlock = lockU.unlock("aaa", "dddd");

        System.out.println(unlock);
        unlock = lockU.unlock("aaa", "dd");

        System.out.println(unlock);

        aa();

        System.out.println(num);

    }


    private static void aa() throws Exception {

        int size = 3;

        CountDownLatch latch = new CountDownLatch(size);
        final String key = RandomUtil.randomString(8);
        System.out.println(key);
        Runnable runnable = () -> {
            Jedis jedis = new Jedis("localhost", 6379);
            LockU lockU = new LockU(jedis);
            for (int i = 0; i < 10000; i++) {
                incr(lockU, key);
            }
        };

        for (int i = 0; i < size; i++) {
            new Thread(runnable).start();
        }

        TimeUnit.SECONDS.sleep(10);

    }


    private static void incr(LockU lockU, String key) {
        UUID uuid = UUID.randomUUID();
        while (true) {
            boolean lock = lockU.tryLock(key, uuid.toString(), 5000);
            if (lock) {
                System.out.println(Thread.currentThread().getId() + " --> " + uuid.toString());
                num++;
                break;
            }

        }

        boolean unlock = lockU.unlock(key, uuid.toString());
        System.out.println(uuid + " -- " + unlock);


    }


}
