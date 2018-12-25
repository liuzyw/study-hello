package com.study.kafka;

import com.study.util.tool.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Create java class named “SimpleProducer"
public class MyProducer {

    private static final Logger logger = LoggerFactory.getLogger(MyProducer.class);

    public static void main(String[] args) throws Exception {
        //Assign topicName to string variable
        String topicName = "topTest2";

        // create instance for properties to access producer configs
        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "myProducer");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        List<String> interectors = new ArrayList<>();
        interectors.add("com.study.kafka.intercetor.TimeIntercetor");
        interectors.add("com.study.kafka.intercetor.CountIntercetor");

        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interectors);

        Producer<String, String> producer = new KafkaProducer<>(props);

        boolean isAsync = true;

        for (int i = 1; i < 30; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName,
                RandomUtil.randomString(6),
                "MessageNo: " + Integer.toString(i) + " " + RandomUtil.randomString(10));
            if (isAsync) {
                producer.send(producerRecord).get();
            } else {
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                        if (exception != null) {
                            logger.error("Send message occurs exception", exception);
                        }
                        if (null != recordMetadata) {
                            System.out.println("offset:" + recordMetadata.offset() + ", partition:%s " +
                                recordMetadata.partition() + ",  send success");
                        }
                    }
                });
            }

        }
        System.out.println("Message send successfully");
        producer.close();
    }
}