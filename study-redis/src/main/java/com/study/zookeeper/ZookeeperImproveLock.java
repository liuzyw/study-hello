package com.study.zookeeper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 不支持重入
 */
public class ZookeeperImproveLock implements Lock {

    private static final String LOCK_PATH = "/LOCK";

    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    private ZkClient client = new ZkClient(ZOOKEEPER_IP_PORT, 1000, 1000, new SerializableSerializer());

    private static Logger logger = LoggerFactory.getLogger(ZookeeperImproveLock.class);

    private CountDownLatch cdl;

    private String beforePath;// 当前请求的节点
    private String currentPath;// 当前请求的节点前一个节点

    // 判断有没有LOCK目录，没有则创建
    public ZookeeperImproveLock() {
        if (!this.client.exists(LOCK_PATH)) {
            this.client.createPersistent(LOCK_PATH);
        }
    }

    public void lock() {
        if (!tryLock()) {
            waitForLock();
            lock();
        } else {
            logger.info(Thread.currentThread().getName() + " 获得分布式锁！");
        }

    }

    /**
     * 为当前节点添加 监听器
     */
    private void waitForLock() {
        IZkDataListener listener = new IZkDataListener() {

            // 删除的时候去监听
            public void handleDataDeleted(String dataPath) throws Exception {
                logger.info(Thread.currentThread().getId() + ":捕获到DataDelete事件！---------------------------");
                if (cdl != null) {
                    cdl.countDown();
                }
            }

            // 发生改变的时候去监听
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }
        };
        // 给之前的节点增加数据删除的watcher
        this.client.subscribeDataChanges(beforePath, listener);

        if (this.client.exists(beforePath)) { // 如果这个节点存在
            cdl = new CountDownLatch(1);
            try {
                cdl.await(); // 线程就给他阻塞，让他等
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.client.unsubscribeDataChanges(beforePath, listener);

    }

    public boolean tryLock() {
        // 如果currentPath为空则为第一次尝试加锁，第一次加锁赋值currentPath
        if (currentPath == null || currentPath.length() <= 0) {
            // 创建一个临时顺序节点
            currentPath = this.client.createEphemeralSequential(LOCK_PATH + '/', "lock");
            System.out.println("---------------------------->" + currentPath);
        }
        // 获取所有临时节点并排序，临时节点名称为自增长的字符串如：0000000400
        List<String> childrens = this.client.getChildren(LOCK_PATH);
        Collections.sort(childrens);

        if (currentPath.equals(LOCK_PATH + '/' + childrens.get(0))) {// 如果当前节点在所有节点中排名第一则获取锁成功
            return true;
        } else {// 如果当前节点在所有节点中排名中不是排名第一，则获取前面的节点名称，并赋值给beforePath
            int wz = Collections.binarySearch(childrens, currentPath.substring(6));
            beforePath = LOCK_PATH + '/' + childrens.get(wz - 1);
        }
        return false;
    }

    public void unlock() {
        // 删除当前临时节点
        client.delete(currentPath);
        currentPath = null;
        client.close();

    }

    // ===================用不到的实现方法=======================

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public Condition newCondition() {
        return null;
    }

}
