package com.study.spring.controller;

import com.study.spring.entity.Fruit;
import com.study.spring.service.FruitService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2017-08-17 21:48
 *
 * @author liuzhaoyuan
 */
@Controller
public class FruitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FruitController.class);


    @Autowired
    private FruitService fruitService;

    @RequestMapping(value = "/findFruitById",method = RequestMethod.POST)
    public String findFruitById(HttpServletRequest request, Model model) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Fruit fruit = fruitService.getFruitById(id);
        LOGGER.info("model add fruit : " + fruit);
        model.addAttribute("fruit", fruit);
        return "fruit/showFruit";
    }

    @RequestMapping(value = "/showFruit",method = RequestMethod.GET)
    public String showFruitByParam(@RequestParam("fruitId") Integer fruitId, Model model) {
        Fruit fruit = fruitService.getFruitById(fruitId);
        LOGGER.info("model add fruit : " + fruit);
        model.addAttribute("fruit", fruit);
        return "fruit/showFruit";
    }

    @RequestMapping(value = "/showFruit/{fruitId}",method = RequestMethod.GET)
    public String showFruitByPath(@PathVariable("fruitId") Integer fruitId, Model model) {
        Fruit fruit = fruitService.getFruitById(fruitId);
        LOGGER.info("model add fruit : " + fruit);
        model.addAttribute("fruit", fruit);
        return "fruit/showFruit";
    }

}
