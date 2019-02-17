package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 2019-02-17
 *
 * @author liuzhaoyuan
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/toLogin")
    public String goLogin() {
        return "login";
    }


    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index.jsp");
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "/loginFail")
    public String loginFail(Model model) {
        model.addAttribute("msg", "用户名或密码错误");
        return "error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LOGGER.info("username:{} , password:{}", username, password);

        return "success";
    }


}
