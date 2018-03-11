package com.study.spring.controller;

import com.study.spring.entity.Bus;
import com.study.spring.entity.Tree;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017-09-24
 *
 * @author liuzhaoyuan
 */
@Controller
public class AjaxController {

    private static List<Bus> buses = new ArrayList<>();

    static {
        Bus bus1 = new Bus();
        bus1.setColor("red");
        bus1.setName("BWM");
        bus1.setDate(new Date());
        buses.add(bus1);

        Bus bus2 = new Bus();
        bus2.setColor("blue");
        bus2.setName("Havav");
        bus2.setDate(new Date());
        buses.add(bus2);

        Bus bus3 = new Bus();
        bus3.setColor("yellow");
        bus3.setName(null);
        buses.add(bus3);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);


    @RequestMapping(value = "/postTree", method = RequestMethod.POST)
    @ResponseBody
    public Tree postList(@RequestBody Tree tree) {
        tree.getLefts().add("one");
        return tree;
    }

    @RequestMapping(value = "/getAjaxMessage", method = RequestMethod.POST)
    @ResponseBody
    public List<Bus> ajaxMessage(@RequestBody Bus bus) {
        System.out.println("----------jaxa----" + bus);

        buses.add(bus);
        LOGGER.info("response ajax: " + buses);
        System.out.println(buses);
        return buses;
    }

    @RequestMapping(value = "/getAjaxMessage1", method = RequestMethod.GET)
    @ResponseBody
    public List<Bus> ajaxMessage1(Bus bus) {
        System.out.println("----------ajaxa----" + bus);

        buses.add(bus);
        LOGGER.info("response ajax: " + buses);
        System.out.println(buses);
        return buses;
    }

    @RequestMapping(value = "/getBuss", method = RequestMethod.POST)
    @ResponseBody
    public List<Bus> JsonServlet1() {
        System.out.println("------- getBuss Post --------");

        LOGGER.info("response ajax post: " + buses);
        System.out.println(buses);
        return buses;
    }

    @RequestMapping(value = "/getBuss1", method = RequestMethod.GET)
    @ResponseBody
    public List<Bus> JsonServlet11() {
        System.out.println("------- getBuss GET --------");

        LOGGER.info("response ajax get: " + buses);
        System.out.println(buses);
        return buses;
    }
}
