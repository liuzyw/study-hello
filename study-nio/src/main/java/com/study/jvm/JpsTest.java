package com.study.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2018-05-19
 *
 * @author liuzhaoyuan
 */
public class JpsTest {

    public static void main(String[] args) throws Exception {
        int i = 0;
        List<Integer> list = new ArrayList<>();
        while (true) {
            TimeUnit.SECONDS.sleep(10);
            byte[] bytes = new byte[1024];
            list.add(i);
            System.out.println(i++);
        }
    }
}
