package com.study.sso.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
//@Repository
public class RedisBaseDao {

//    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void addValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
