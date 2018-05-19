package com.study.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018-05-19
 *
 * @author liuzhaoyuan
 */
@Controller
public class MapController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);


    @RequestMapping(value = "/goMap")
    public String goPage() {
        return "map/map";
    }

}
