package com.study.shiro.controller;

import com.study.shiro.service.HelloService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-01-08
 *
 * @author liuzhaoyuan
 */
@Controller
public class HomeController {

    @Autowired
    private HelloService helloService;

    /**
     * 注解不需要登录即可访问
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        helloService.hello();
        return "hello word is anon";
    }

    @RequestMapping("/hello1")
    @ResponseBody
    @RequiresAuthentication
    public String hello1() {
        helloService.hello1();
        return "hello word 1 is login";
    }

    @RequestMapping("/hello2")
    @ResponseBody
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.AND)
    public String hello2() {
        helloService.hello2();
        return "hello word 2 is roles";
    }

    @RequestMapping("/hello3")
    @RequiresPermissions(value = {"user:add", "user:find"}, logical = Logical.OR)
    @ResponseBody
    public String hello3() {
        helloService.hello3();
        return "hello word 3 is permission";
    }


    @RequestMapping("/view/user")
    public String toUser() {
        return "views/user";
    }


    @RequestMapping("/view/admin")
    public String toAdminr() {
        return "views/admin";
    }

    @RequestMapping("/session")
    @ResponseBody
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("key_aaa", "abcdefghijk");
        // service 层可以用 shiro 的session 功能获取session
        return helloService.session();

    }

}
