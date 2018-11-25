package com.study.zookeeper;

import java.util.concurrent.TimeUnit;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created on 2018-11-24
 *
 * @author liuzhaoyuan
 */
public class ZkLockDemo {

    private static int num = 0;

    private static String path = "/zk-lo3";

    public static void main(String[] args) throws Exception {

        Runnable runnable = () -> {
            ZkClient zkClient = new ZkClient("127.0.0.1:2181");
            System.out.println("ZK 成功建立连接！");

            ZkLock lock = new ZkLock();

            for (int i = 0; i < 1; i++) {
                incr(zkClient, lock);
            }


        };

        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }

        TimeUnit.SECONDS.sleep(10);

        System.out.println(num);

    }


    private static void incr(ZkClient zkClient, ZkLock lock) {
        lock.getLock(zkClient, path);

        num++;

        lock.unlock(zkClient, path);

    }

}
