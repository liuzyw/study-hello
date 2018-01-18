package com.study.sso.controller;

import com.study.sso.util.CookieUtils;
import com.study.util.date.DateUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-01-04
 *
 * 完全同域的 sso
 *
 * @author liuzhaoyuan
 */
@Controller
public class SameDomainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SameDomainController.class);

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        LOGGER.info(" spring boot web log home");
        return "--- Hello Spring Boot sso Test --- \n" + DateUtils.getCurTimeStr();
    }

    @RequestMapping("/sameDomain1")
    public String sameDomain1(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtils.checkCookie(request, "sso-cookie", "sso")) {
            return "sameDomain/sameDomain1";
        } else {
            request.setAttribute("goUrl","sameDomain/sameDomain1");
            return "login";
        }
    }

    @RequestMapping("/sameDomain2")
    public String sameDomain2(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtils.checkCookie(request, "sso-cookie", "sso")) {
            return "sameDomain/sameDomain2";
        } else {
            request.setAttribute("goUrl","sameDomain/sameDomain2");
            return "login";
        }
    }

}
