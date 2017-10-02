package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2017-10-02
 *
 * @author liuzhaoyuan
 */
@Controller
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/addToRedis", method = RequestMethod.POST)
    public String addToRedis(HttpServletRequest request, Model model) {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        redisTemplate.opsForValue().set(key, value);

        model.addAttribute("redisKey", key);
        model.addAttribute("redisValue", value);
        return "redis/showRedis";
    }

    @RequestMapping(value = "/showRedisValue", method = RequestMethod.GET)
    public String showRedisValue(@RequestParam("key") String key, Model model) {
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("get redis key: " + key + ", value=" + value);
        model.addAttribute("redisKey", key);
        model.addAttribute("redisValue", value);
        return "redis/showRedis";
    }
}
