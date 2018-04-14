package com.study.kafka;

import java.util.Properties;
import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.Map;
import scala.collection.Seq;

/**
 * Created on 2018-04-10
 *
 * @author liuzhaoyuan
 */
public class KafkaUtils {

    private static String ZK_CONNECT = "localhost:9092";
    private static final int SESSION_TIMEOUT = 30000;
    private static final int CONNECT_TIMEOUT = 30000;


    /**
     * 创建 Topic
     */
    public static void createTopic(String topic, int partition, int replication, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if (!AdminUtils.topicExists(zkUtils, topic)) {
                AdminUtils.createTopic(zkUtils, topic, partition, replication, properties,
                    AdminUtils.createTopic$default$6());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 修改主题级别配置的具体实现
     */
    public static void modifyTopicConfig(String topic, Properties properties) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            //首先获取当前己有的配置，这里是查询主题级别的配置，因此指定配置类型为 Topic 添加新修改的配置
            Properties curProp = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), topic);
            curProp.putAll(properties);
            AdminUtils.changeTopicConfig(zkUtils, topic, curProp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 主题增加一个分区
     */
    public static void addPartitions(String topic, int numPartitions, String replicaAssignmentStr) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            AdminUtils.addPartitions(zkUtils, topic, numPartitions, replicaAssignmentStr, true,
                AdminUtils.addPartitions$default$6());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 修改分区及副本数的核心代码
     */
    public static void modifyPartitions(String topic) {
        ZkUtils zkUtils = null;
        try {
            zkUtils = ZkUtils.apply(ZK_CONNECT, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            // 2.获取代理元数据信息
            Seq<BrokerMetadata> brokerMeta = AdminUtils
                .getBrokerMetadatas(zkUtils, AdminUtils.getBrokerMetadatas$default$2(),
                    AdminUtils.getBrokerMetadatas$default$3());
            // 3.生成分区副本分配方案: 2 个分区、 3 个副本
            Map<Object, Seq<Object>> replicaAssign = AdminUtils.assignReplicasToBrokers
                (brokerMeta, 2, 3, AdminUtils.assignReplicasToBrokers$default$4(),
                    AdminUtils.assignReplicasToBrokers$default$5());
            // 4.修改分区副本分配方案
            AdminUtils.createOrUpdateTopicPartitionAssignmentPathInZK(zkUtils, topic, replicaAssign, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }

}
