package com.study.zookeeper.demo;

import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Created on 2018-12-27
 *
 * @author liuzhaoyuan
 */
public class Dea {


    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        long sessionId = zooKeeper.getSessionId();

        System.out.println("sessionid: " + sessionId);

        // 创建节点

        String s = zooKeeper.create("/hello/efdf", "liu".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("create path: " + s);

        // 获取直接点

        List<String> children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        children.stream().forEach(System.out::println);

        // 判断节点是否存在

        Stat exists = zooKeeper.exists("/hello", false);
        System.out.println("exists:" + exists);

        zooKeeper.close();


    }

}
