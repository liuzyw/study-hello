package com.study.stream.controller;

import com.study.stream.SendService;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() {
        String content = "Hello World Send msg " + DateUtils.addDays(new Date(), 0);
        Message msg = MessageBuilder.withPayload(content.getBytes()).build();
        sendService.sendOrder().send(msg);
        return content;
    }

}
