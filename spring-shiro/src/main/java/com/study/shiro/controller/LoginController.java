package com.study.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2018-01-09
 *
 * @author liuzhaoyuan
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //4、登录，即身份验证
        try {
            subject.login(token);
            System.out.println("login success");
            LOGGER.info(" ---------    login success   -----");

//            token.setRememberMe(true);
            return "views/success";
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("login fail");
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/loginout")
    public String loginout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index.jsp";
    }
}
