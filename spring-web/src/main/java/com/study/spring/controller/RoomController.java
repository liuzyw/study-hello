package com.study.spring.controller;

import com.study.spring.room.RoomResources;
import com.study.spring.vo.RoomPair;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-05
 *
 * <p>一个会议室抢订</p>
 *
 * @author liuzhaoyuan
 */
@Controller
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/goRoom", method = RequestMethod.GET)
    public String toPage(Model model) {
        model.addAttribute("rooms", RoomResources.getRoomList());
        return "room/showRoom";
    }

    @RequestMapping(value = "/bookRoom", method = RequestMethod.POST)
    @ResponseBody
    public boolean bookRoom(@RequestBody RoomPair roomPair, Model model) {
        LOGGER.info("booking param: " + roomPair);
        boolean result = RoomResources.booking(roomPair);
        LOGGER.info("booking room: " + result);
        return result;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public boolean signIn(@RequestBody RoomPair roomPair) {
        boolean re = RoomResources.signIn(roomPair);
        LOGGER.info("signIn result:{}", re);
        return true;
    }


}
