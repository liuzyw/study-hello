package com.study.sso.controller;

import com.study.sso.util.CookieUtils;
import com.study.sso.util.SSOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String goUrl = request.getParameter("goUrl");
        LOGGER.info("login request, username:{}, password:{}", name, pass);

        boolean isOk = SSOUtils.checkLogin(name, pass);
        if (isOk) {
            CookieUtils.addCookie(response, "sso-cookie", "sso");
            LOGGER.info("add to cookie ...");
            return "redirect:" + goUrl + ".html";
        }
        return "login";
    }

}
