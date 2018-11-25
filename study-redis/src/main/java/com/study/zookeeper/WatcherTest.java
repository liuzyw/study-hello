package com.study.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created on 2018-11-24
 *
 * @author liuzhaoyuan
 */
public class WatcherTest {

    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        System.out.println("ZK 成功建立连接！");

        String path = "/watcher-a";

        zkClient.createPersistent(path);

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String parentPath, Object currentChilds) throws Exception {
                System.out.println("路径" + parentPath + "下面的节点变更为：" + currentChilds);

            }

            @Override
            public void handleDataDeleted(String parentPath) throws Exception {
                System.out.println("路径" + parentPath + "下面的节点变删除：");

            }
        };

        zkClient.subscribeDataChanges(path,listener);


        while (true);

    }
}
