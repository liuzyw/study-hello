package com.study.spring.controller;

import com.study.spring.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2017-10-03
 *
 * @author liuzhaoyuan
 */
@Controller
public class MailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    @RequestMapping("/sendSimpleMail")
    public String sendSimpleMail(){
        boolean result = mailService.sendSimpleEmail();
        if (!result){
            LOGGER.warn("邮件发送失败");
        }
        return "success";
    }
}
