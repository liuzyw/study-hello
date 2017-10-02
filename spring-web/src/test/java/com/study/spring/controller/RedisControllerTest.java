package com.study.spring.controller;

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
    "classpath:applicationContext.xml"})
public class RedisControllerTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testopsForValue() {
        String value = (String) redisTemplate.opsForValue().get("hello");
        System.out.println(value);
    }

}
