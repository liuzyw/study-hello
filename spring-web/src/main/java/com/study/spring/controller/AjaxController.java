package com.study.spring.controller;

import com.study.spring.po.Bus;
import java.util.ArrayList;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);

    @RequestMapping(value = "/getAjaxMessage", method = RequestMethod.POST)
    @ResponseBody
    public List<Bus> ajaxMessage(@RequestBody Bus bus) {
        System.out.println("----------jaxa----" + bus);
        List<Bus> buss = new ArrayList<>();
        Bus bus1 = new Bus();
        bus1.setColor("red");
        bus1.setName("BWM");
        buss.add(bus1);

        Bus bus2 = new Bus();
        bus2.setColor("blue");
        bus2.setName("Havav");
        buss.add(bus2);

        buss.add(bus);
        LOGGER.info("response ajax: " + buss);
        System.out.println(buss);
        return buss;
    }

    @RequestMapping(value = "/getAjaxMessage1", method = RequestMethod.GET)
    @ResponseBody
    public List<Bus> ajaxMessage1(Bus bus) {
        System.out.println("----------ajaxa----" + bus);
        List<Bus> buss = new ArrayList<>();
        Bus bus1 = new Bus();
        bus1.setColor("red");
        bus1.setName("BWM");
        buss.add(bus1);

        Bus bus2 = new Bus();
        bus2.setColor("blue");
        bus2.setName("Havav");
        buss.add(bus2);

        buss.add(bus);
        LOGGER.info("response ajax: " + buss);
        System.out.println(buss);
        return buss;
    }

    @RequestMapping(value = "/getBuss", method = RequestMethod.POST)
    @ResponseBody
    public List<Bus> JsonServlet1() {
        System.out.println("------- getBuss Post --------");
        List<Bus> buss = new ArrayList<>();
        Bus bus1 = new Bus();
        bus1.setColor("red");
        bus1.setName("BWM");
        buss.add(bus1);

        Bus bus2 = new Bus();
        bus2.setColor("blue");
        bus2.setName("Havav");
        buss.add(bus2);

        LOGGER.info("response ajax post: " + buss);
        System.out.println(buss);
        return buss;
    }

    @RequestMapping(value = "/getBuss1", method = RequestMethod.GET)
    @ResponseBody
    public List<Bus> JsonServlet11() {
        System.out.println("------- getBuss GET --------");
        List<Bus> buss = new ArrayList<>();
        Bus bus1 = new Bus();
        bus1.setColor("red");
        bus1.setName("BWM");
        buss.add(bus1);

        Bus bus2 = new Bus();
        bus2.setColor("blue");
        bus2.setName("Havav");
        buss.add(bus2);

        LOGGER.info("response ajax get: " + buss);
        System.out.println(buss);
        return buss;
    }
}
