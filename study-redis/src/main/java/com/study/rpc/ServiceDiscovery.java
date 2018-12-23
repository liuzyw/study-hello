package com.study.rpc;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created on 2018-12-15
 *
 * @author liuzhaoyuan
 */
public class ServiceDiscovery {

    private List<String> serviceRepos = null;

    private LoadBalanceStrategy loadBalanceStrategy = new RandomLoadBalance();

    private CuratorFramework curatorFramework;

    private static final String REGISTERY_ROOT = "/registrys/";


    {
        curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(1000).retryPolicy(new ExponentialBackoffRetry(2000, 10)).build();

        curatorFramework.start();
    }


    public void init(String serviceName) {

        String servicePath = REGISTERY_ROOT + serviceName;
        try {
            serviceRepos = curatorFramework.getChildren().forPath(servicePath);

            // 注册 watcher
            registerWatcher(servicePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerWatcher(String servicePath) {

        try {
            PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, servicePath, true);
            PathChildrenCacheListener listener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    serviceRepos = curatorFramework.getChildren().forPath(servicePath);
                }
            };

            childrenCache.getListenable().addListener(listener);
            childrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServiceEndPoint() {
        return loadBalanceStrategy.selectHost(serviceRepos);
    }

    public static void main(String[] args) {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery();

        serviceDiscovery.init("orderService");

        for (int i = 0; i < 10; i++) {
            System.out.println(serviceDiscovery.getServiceEndPoint());

        }

    }

}
