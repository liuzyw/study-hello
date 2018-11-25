package com.study.zookeeper;

import java.util.concurrent.CountDownLatch;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created on 2018-11-24
 *
 * @author liuzhaoyuan
 */
public class ZkLock {

    private static final String PATH = "lock";


    private static CountDownLatch latch=new CountDownLatch(1);

    public ZkLock() {
    }

    public void lock(String key, String value, int time) {

    }


    public void lock(String key, String value, int time, int sleep) {
    }

    public boolean tryLock(ZkClient zkClient,String key) {

        try {
            zkClient.createEphemeral(key);
            return true;
        } catch (RuntimeException e) {
        }

        return false;
    }

    public void getLock(ZkClient zkClient,String key) {
        boolean tryLock = tryLock(zkClient,key);
        if (!tryLock) {
            waitLock(zkClient,key);
            getLock(zkClient,key);
        }

    }

    public void waitLock(ZkClient zkClient,String key) {

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (latch != null) {
                    System.out.println("down ...");
                    latch.countDown();
                }
            }
        };

        zkClient.subscribeDataChanges(key, listener);

        if (zkClient.exists(key)) {
            latch = new CountDownLatch(1);
            System.out.println("await ....");
            try {
                latch.await();
            } catch (Exception e) {

            }
        }
        zkClient.unsubscribeDataChanges(key, listener);

    }


    public void unlock(ZkClient zkClient,String key) {
        try {
            zkClient.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
