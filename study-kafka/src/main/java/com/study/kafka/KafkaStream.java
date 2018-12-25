package com.study.kafka;

import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;

/**
 * Created on 2018-12-25
 *
 * @author liuzhaoyuan
 */
public class KafkaStream {

    public static void main(String[] args) {

//        TopologyBuilder topologyBuilder = new TopologyBuilder();
//        topologyBuilder.addSource("SOURCE","topTest2")
//        .addProcessor("PROCESSOR","SOURCE");
//
//
//        Properties props = new Properties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//        KafkaStreams kafkaStreams = new KafkaStreams(topologyBuilder, props);
//
//        kafkaStreams.start();

    }

}
