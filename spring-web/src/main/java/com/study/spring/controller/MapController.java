package com.study.spring.controller;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-19
 *
 * @author liuzhaoyuan
 */
@Controller
public class MapController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);


    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = "/goMap")
    public String goPage() {
        return "map/map";
    }


    @RequestMapping(value = "/threadPool")
    @ResponseBody
    public String testThreadPool() {

        for (int i = 0; i < 100; i++) {
            final int j = i;
            threadPoolTaskExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    LOGGER.info("threadPoolPrint:{} ", j);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return "success";
    }

}
