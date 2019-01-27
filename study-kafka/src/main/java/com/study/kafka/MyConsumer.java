package com.study.kafka;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MyConsumer.class);


    public static void main(String[] args) throws Exception {
        //Kafka consumer configuration settings
        String topicName = "topTest2";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");

        props.put("auto.offset.reset", "latest");

        props.put("enable.auto.commit", "true");


        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));
        //print the topic name
        System.out.println("Subscribed to topic " + topicName);

        try {
            while (true) {
                //长轮询拉取消息
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    // print the offset,key and value for the consumer records.
                    System.out
                        .println(
                            "offset: " + record.offset() + ", key: " + record.key() + ", value: " + record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}