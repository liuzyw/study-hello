package com.study.sso.controller;

import com.study.sso.prop.MyProperties;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2018-01-18
 *
 * @author liuzhaoyuan
 */
@Controller
public class HelloController {

    @Value("${my.value.name}")
    private String myValueName;

    @Resource
    private MyProperties myProperties;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("----  hello  ----");
        return "hello spring boot 学习 " + myValueName + "<br/>" + myProperties.getBook();
    }

    @RequestMapping("/model/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> list = new ArrayList<>();
        list.add(100);
        modelAndView.addObject("myName", "Tom");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("model");
        return modelAndView;
    }

    @RequestMapping("/model/view1")
    public String view1(Model model) {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        model.addAttribute("myName", "Tom");
        model.addAttribute("list", list);
        return "model";
    }

}
