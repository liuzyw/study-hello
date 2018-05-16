package com.study.spring.cloud.controller;

import com.study.spring.cloud.entity.Fruit;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-05-15
 *
 * @author liuzhaoyuan
 */
@RestController
public class RoncooController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoncooController.class);


    @GetMapping(value = "/roncoo/hello")
    public String gethello() {
        return "hello roncoo";
    }

    @GetMapping(value = "/roncoo/getPriceByFruitId")
    public String getPrice(@RequestParam("fruitId") Integer fruitId) {
        return "110.23";
    }

    @GetMapping(value = "/roncoo/getWithTime")
    public String getWithTime(@RequestParam("time") Integer time) {

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "time";
    }

    @GetMapping(value = "/roncoo/getWithError")
    public String getWithTime() {

        int a = 0;
        int b = 10 / a;

        return "time" + b;
    }


    @PostMapping(value = "/roncoo/findFruitById")
    public Fruit postProduct(HttpServletRequest request) {
        String id = request.getParameter("id");
        Fruit fruit = new Fruit();
        fruit.setId(Integer.valueOf(id));
        fruit.setName("apple");
        fruit.setPrice(new BigDecimal("8.0"));
        fruit.setWeight(0.4D);
        return fruit;
    }

}
