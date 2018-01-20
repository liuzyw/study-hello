package com.study.sso.controller;

import com.study.sso.po.User;
import com.study.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findUser")
    public User findUser() {
        return userService.findByUsername("zhang");
    }

}
