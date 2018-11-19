package netty.dubbo.server.impl;

import netty.dubbo.server.RegistCenter;
import netty.dubbo.server.config.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public class RegistCenterImpl implements RegistCenter {


    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(ZkConfig.CONNECTION_STR).sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(100, 3)).build();

        curatorFramework.start();
    }


    @Override
    public void register(String serviceName, String ServiceUrl) {

        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;

        try {

            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(servicePath, "0".getBytes());
            }

            String addressPath = servicePath + "/" + ServiceUrl;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                .forPath(addressPath, "0".getBytes());

            System.out.println("- server register success -- " + rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
