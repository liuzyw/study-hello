package com.study.kafka.day1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018-12-24
 *
 * @author liuzhaoyuan
 */
public class LowerConsumer {


    public static void main(String[] args) {

        List<String> brokers = new ArrayList<>();

        brokers.add("127.0.0.1:2181");

        String topic = "topTest2";

        int partition = 0;

        long offset = 20;


    }


    private String findLeader(List<String> brokers, String topic, int partition) {



        return "";
    }

    private void getDate() {

    }

}
