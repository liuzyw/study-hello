package com.study.spring.controller;

import com.study.spring.po.User;
import com.study.spring.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017-08-17 23:39
 *
 * @author liuzhaoyuan
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserById")
    public String findUserById(HttpServletRequest request, Model model) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        User user = userService.getUserById(id);

        LOGGER.info("model add user : " + user);

        model.addAttribute("user", user);

        return "user/showUser";

    }

}
