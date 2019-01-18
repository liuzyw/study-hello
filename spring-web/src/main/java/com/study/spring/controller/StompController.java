package com.study.spring.controller;

import com.study.spring.vo.ChatRoomRequest;
import com.study.spring.vo.ChatRoomResponse;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created on 2019-01-18
 *
 * @author liuzhaoyuan
 */
@Controller
public class StompController implements Serializable {


    private static final Logger LOGGER = LoggerFactory.getLogger(StompController.class);

//    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    private static final Long serialVersionUID = 1L;

    @MessageMapping("/massRequest")
    @SendTo("/mass")
    public ChatRoomResponse massRequest(ChatRoomRequest roomRequest) {

        LOGGER.info("receive mass info:{}", roomRequest);

        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
        chatRoomResponse.setFromName(roomRequest.getFromName());
        chatRoomResponse.setChatValue(roomRequest.getChatValue());
        return chatRoomResponse;
    }


    @MessageMapping("/aloneRequest")
    public void aloneRequest(ChatRoomRequest roomRequest) {

        LOGGER.info("receive mass info:{}", roomRequest);
        ChatRoomResponse chatRoomResponse = new ChatRoomResponse();
        chatRoomResponse.setChatValue(roomRequest.getChatValue());
        chatRoomResponse.setFromName(roomRequest.getFromName());

        simpMessagingTemplate.convertAndSendToUser(roomRequest.getToUserId(), "/alone", chatRoomResponse);


    }

}
