package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018-01-04
 *
 * @author liuzhaoyuan
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/toLogin")
    public String toLogin() {
        LOGGER.info(" spring boot sso login request");
        return "login";
    }

    @RequestMapping("/login")
    public String doLogin(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        LOGGER.info("login request, username:{}, password:{}", name, pass);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, pass);

        subject.login(token);

        if (subject.isAuthenticated()) {
            return "success";
        } else {
            return "login";
        }

    }

}
