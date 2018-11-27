package com.study.spring.controller;

import com.study.spring.entity.Bus;
import com.study.spring.entity.Tree;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * ajax post 单个请求
     * <p>
     * application/x-www-form-urlencoded
     * <p>
     * produces 防止返回给前端中文乱码
     *
     * @return
     */
    @RequestMapping(value = "/postOne", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public String postOne(HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println("----------jaxa----" + name);

        return name;
    }

    /**
     * ajax post 单个请求
     *
     * @return
     */
    @RequestMapping(value = "/getAjaxMessage", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public List<Bus> ajaxMessage(@RequestBody Bus bus) {
        System.out.println("----------jaxa----" + bus);
        buses.add(bus);
        LOGGER.info("response ajax: " + buses);
        System.out.println(buses);
        return buses;
    }

    /**
     * ajax GET request 获取中文需要编码
     *
     * @return
     */
    @RequestMapping(value = "/getAjaxMessage1", method = RequestMethod.GET, consumes = {"application/json;charset=UTF-8"}, produces = {"text/html;charset=UTF-8",
        "application/json;charset=UTF-8"})
    @ResponseBody
    public List<Bus> ajaxMessage1(HttpServletRequest request, Bus bus) {
        System.out.println("----------ajaxa----" + bus);
        System.out.println(request.getParameter("name"));

        try {
            String keyword = URLDecoder.decode(request.getParameter("name"), "UTF-8");

            System.out.println(keyword);

            String s = new String(bus.getName().getBytes("iso8859-1"), "UTF-8");
            System.out.println(s);
            String s2 = new String(bus.getName().getBytes("UTF-8"), "UTF-8");
            System.out.println(s2);
            bus.setName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @RequestMapping(value = "/respEntity", produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    public ResponseEntity<String> download(HttpServletRequest request) throws IOException {
        return new ResponseEntity<>("213展示", HttpStatus.OK);
    }
}
