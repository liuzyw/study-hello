package com.study.spring.controller;

import com.study.spring.entity.Chat;
import com.study.spring.vo.Result;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019-01-13
 *
 * @author liuzhaoyuan
 */
@Controller
public class ChatController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    private static final Long serialVersionUID = 1L;


    @RequestMapping("/goChat")
    public String goChat() {
        return "chat/chat";
    }


    @ResponseBody
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public Result<String> sendMsg(@RequestBody Chat chat) {

        LOGGER.info("chat:{}", chat);

        return new Result<>("发送成功", 200);
    }


}
