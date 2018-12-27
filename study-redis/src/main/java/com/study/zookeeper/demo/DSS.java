package com.study.zookeeper.demo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created on 2018-12-27
 *
 * @author liuzhaoyuan
 */
public class DSS implements Serializable {


    private ZooKeeper zooKeeper;

    private static final Long serialVersionUID = 1L;

    private static String server = "/registrys";


    public static void main(String[] args) throws Exception {

        DSS dss = new DSS();
        dss.getConnect();

        // 注册节点
//        String s = dss.zooKeeper.create(server + "/" + "receiptService", "liu".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        String s = "/registrys/receiptService";

        System.out.println(s);

        String s1 = dss.zooKeeper.create(s + "/" + "123.3.42.2:99", "liu2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        String s2 = dss.zooKeeper.create(s + "/" + "123.3.43.2:99", "liu3".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        String s3 = dss.zooKeeper.create(s + "/" + "123.3.44.2:99", "liu4".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        Scanner scanner = new Scanner(System.in);

        while (true){
            String line = scanner.nextLine();

            if (line.equals("1")){
                dss.zooKeeper.delete(s1, -1);
            }
            if (line.equals("2")){
                dss.zooKeeper.delete(s2, -1);
            }
            if (line.equals("3")){

                dss.zooKeeper.delete(s3, -1);
            }

            if (line.equals("88")){
                break;
            }
        }

//        dss.zooKeeper.delete(s, -1);

        dss.close();

    }

    public void getConnect() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
