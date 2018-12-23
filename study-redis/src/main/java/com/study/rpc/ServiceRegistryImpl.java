package com.study.rpc;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created on 2018-12-15
 *
 * @author liuzhaoyuan
 */
public class ServiceRegistryImpl implements ServiceRegistry {

    private static CuratorFramework curatorFramework;

    private static final String REGISTERY_ROOT = "/registrys/";


    static {
        curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(1000).retryPolicy(new ExponentialBackoffRetry(2000, 10)).build();

        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String ServiceUrl) {

        String servicePath = REGISTERY_ROOT + serviceName;

        try {
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }

            String addressPath = servicePath + "/" + ServiceUrl;
            String node = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());

            System.out.println("service register success ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ServiceRegistry serviceRegistry = new ServiceRegistryImpl();

        System.out.println("register center start ....");


        serviceRegistry.register("orderService", "127.0.0.1:8080");
        serviceRegistry.register("orderService", "127.0.0.1:8081");
        serviceRegistry.register("orderService", "127.0.0.1:8082");
        serviceRegistry.register("orderService", "127.0.0.1:8083");
        serviceRegistry.register("orderService", "127.0.0.1:8084");
        serviceRegistry.register("orderService", "127.0.0.1:8085");
        serviceRegistry.register("orderService", "127.0.0.1:8086");
        serviceRegistry.register("orderService", "127.0.0.1:8087");
        serviceRegistry.register("orderService", "127.0.0.1:8088");
        serviceRegistry.register("orderService", "127.0.0.1:8089");


        System.in.read();
    }
}
