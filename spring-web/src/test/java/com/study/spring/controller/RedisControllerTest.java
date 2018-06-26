package com.study.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2017-10-02
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class RedisControllerTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testopsForValue() {
        String value = (String) redisTemplate.opsForValue().get("hello");
        System.out.println(value);

        Integer[] aa = new Integer[]{1,2};

        redisTemplate.opsForValue().set("aa",aa);

        try {
            System.out.println(redisTemplate.opsForValue().get("aa"));
        }catch (Exception e){
            System.out.println("-- aa ---");
        }

        List<Integer> bb = new ArrayList<>();
        bb.add(11);
        bb.add(22);
        redisTemplate.opsForValue().set("bb",bb);

        try {
            List<Integer> bb1 = (List<Integer>) redisTemplate.opsForValue().get("bb");
            System.out.println(bb1);
        }catch (Exception e){
            System.out.println("-- bb ---");
        }


    }

}
