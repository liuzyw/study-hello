package com.study.spring.controller;

import com.study.spring.jvm.JVMUtil;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-20
 *
 * @author liuzhaoyuan
 */
@Controller
public class JavaJVMController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaJVMController.class);

    @RequestMapping(value = "/goJavaJVM")
    public String goPage() {
        return "java/jvm";
    }


    @RequestMapping(value = "/getJVMInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getJVMInfo() {
        LOGGER.info("getJVMInfo ...");
        return JVMUtil.getJvmInfo();
    }
}
