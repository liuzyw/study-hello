package com.study.hello.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheService {

    @Autowired
    private RestTemplate restTemplate;

    @CacheResult
    @HystrixCommand
    public String cacheMember(Integer id) {
        System.out.println("调用 cacheMember 方法: " + id);
        String member = restTemplate.getForEntity("http://spring-cloud-eureka-producer/hello", String.class).getBody();
        return member;
    }

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public String getCache(Integer id) {
        System.out.println("执行查询方法: " + id);
        return "getCache " + id;
    }

    @CacheRemove(commandKey = "cacheKey")
    @HystrixCommand
    public void removeCache(Integer id) {
        System.out.println("删除缓存方法: " + id);
    }
}
