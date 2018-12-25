package com.study.kafka.intercetor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Created on 2018-12-25
 *
 * @author liuzhaoyuan
 */
public class CountIntercetor implements ProducerInterceptor<String, String> {

    private AtomicInteger success = new AtomicInteger(0);
    private AtomicInteger fail = new AtomicInteger(0);


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            success.incrementAndGet();

        } else {
            fail.incrementAndGet();
        }
    }

    @Override
    public void close() {

        System.out.println("send success: " + success);
        System.out.println("send fail: " + fail);

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
