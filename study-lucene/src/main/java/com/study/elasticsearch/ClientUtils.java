package com.study.elasticsearch;

import java.net.InetSocketAddress;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Created on 2019-03-02
 *
 * @author liuzhaoyuan
 */
public class ClientUtils {

    private static final Long serialVersionUID = 1L;

    public static String CLUSTER_NAME = "elasticsearch";//集群名称
    public static String HOST_IP = "127.0.0.1";//服务器 IP
    public static int TCP_PORT = 9300;//端口


    public static TransportClient getClient() {
        Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME).build();

        TransportClient client = new PreBuiltTransportClient(settings)
            .addTransportAddress(new TransportAddress(new InetSocketAddress(HOST_IP, TCP_PORT)));

        return client;
    }

}
