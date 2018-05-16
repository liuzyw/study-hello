package com.study.hystrix.service;

import com.study.hystrix.entity.Fruit;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2018-05-15
 *
 * @author liuzhaoyuan
 */
@Service
public class RoncooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoncooService.class);

    @Autowired
    private RestTemplate restTemplate;


    public String hello() {
        ResponseEntity<String> member = restTemplate.getForEntity("http://spring-cloud-eureka-producer/roncoo/hello", String.class);
        return member.getBody();
    }

    public String getPrice(Integer fruitId) {
        Map<String, String> map = new HashMap<>();
        map.put("fruitId", "" + fruitId);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://spring-cloud-eureka-producer/roncoo/getPriceByFruitId?fruitId={fruitId}", String.class, map);
        return responseEntity.getBody();
    }


    public String getWithTime(Integer time) {
        Map<String, String> map = new HashMap<>();
        map.put("time", "" + time);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://spring-cloud-eureka-producer/roncoo/getWithTime?time={time}", String.class, map);
        return responseEntity.getBody();
    }


    public String getWithTime() {
        ResponseEntity<String> member = restTemplate.getForEntity("http://spring-cloud-eureka-producer/roncoo/getWithError", String.class);
        return member.getBody();
    }

    public Fruit postProduct(Integer id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "" + id);
        ResponseEntity<Fruit> member = restTemplate.postForEntity("http://spring-cloud-eureka-producer/roncoo/getWithError", null, Fruit.class, map);
        return member.getBody();
    }

}
