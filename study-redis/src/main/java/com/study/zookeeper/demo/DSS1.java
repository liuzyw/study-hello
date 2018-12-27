package com.study.zookeeper.demo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created on 2018-12-27
 *
 * @author liuzhaoyuan
 */
public class DSS1 implements Serializable {


    private ZooKeeper zooKeeper;

    private static final Long serialVersionUID = 1L;

    private static String server = "/registrys";


    public static void main(String[] args) throws Exception {

        DSS1 dss = new DSS1();
        dss.getConnect();

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        dss.close();

    }

    public void getConnect() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    watcher();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void watcher() {
        List<String> children = null;
        try {
            children = zooKeeper.getChildren(server + "/" + "receiptService", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (children == null || children.size() == 0) {
            return;
        }

        children.stream().forEach((str) -> {
            try {
                byte[] data = zooKeeper.getData(server + "/" + "receiptService" + "/" + str, false, null);

                System.out.println(new String(data));

            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("----->");
    }

    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
