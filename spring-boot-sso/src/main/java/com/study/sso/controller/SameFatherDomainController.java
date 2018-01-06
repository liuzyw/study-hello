package com.study.sso.controller;

import com.study.sso.util.CookieUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2018-01-06
 *
 * 同父域的 sso
 *
 * @author liuzhaoyuan
 */
public class SameFatherDomainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SameFatherDomainController.class);


    @RequestMapping("/sameDomain1")
    public String sameDomain1(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtils.checkCookie(request, "sso-cookie", "sso")) {
            LOGGER.info(" find cookie");
            return "sameDomain/sameDomain1";
        } else {
            request.setAttribute("goUrl", "sameDomain/sameDomain1");
            return "login";
        }
    }

    @RequestMapping("/sameDomain2")
    public String sameDomain2(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtils.checkCookie(request, "sso-cookie", "sso")) {
            LOGGER.info(" find cookie");
            return "sameDomain/sameDomain2";
        } else {
            request.setAttribute("goUrl", "sameDomain/sameDomain2");
            return "login";
        }
    }
}
