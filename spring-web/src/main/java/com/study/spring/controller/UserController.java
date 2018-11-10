package com.study.spring.controller;

import com.study.spring.entity.User;
import com.study.spring.service.UserService;
import com.study.spring.util.VerificationCodeUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created on 2017-08-17 23:39
 *
 * @author liuzhaoyuan
 */
@Controller
@SessionAttributes("user")
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

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {

        LOGGER.info("to admin : ");

        return "user/admin";

    }

    @RequestMapping("/goLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        LOGGER.info("username: " + username);
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        LOGGER.info("session user:{}", request.getSession().getAttribute("user"));
        if (code.equals(request.getSession().getAttribute("code"))) {
            return "login success, " + username + ", " + password + ", " + code;
        } else {
            return "login fail";
        }
    }

    @RequestMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) {
        // 将四位数字的验证码保存到Session中。
        String randomCode = VerificationCodeUtils.getCode(request, response);
        HttpSession session = request.getSession();
        System.out.print("randomCode: " + randomCode);
        session.setAttribute("code", randomCode.toString());
    }

}
