package com.study.spring.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-18
 *
 * @author liuzhaoyuan
 */
@Controller
public class ShortLinkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortLinkController.class);

    @RequestMapping(value = "/goShortLink")
    public String toShortLinkPage() {
        return "shortlink/link";
    }

    @RequestMapping(value = "/compressUrl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> compressUrl(HttpServletRequest request) {

        String longurl = request.getParameter("longurl");
        LOGGER.info("longurl:{} ", longurl);

        Map<String, String> map = new HashMap<>();
        map.put("shortlink", "dasdasdas");

        return map;
    }

}
