package com.study.spring.controller;

import com.google.common.collect.Lists;
import com.study.spring.vo.BookRoomVO;
import com.study.spring.vo.RoomPair;
import com.study.spring.vo.RoomVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created on 2018-05-05
 *
 * <p>一个会议室抢订</p>
 *
 * @author liuzhaoyuan
 */
@Controller
@SessionAttributes("rooms")
public class RoomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);


    //    @Value("${room.count}")
    private static int roomCount = 3;


    @Autowired
    private HttpServletRequest request;

    private List<RoomVO> list;


    @RequestMapping(value = "/goRoom", method = RequestMethod.GET)
    public String toPage(Model model) {
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.info("init room count:{}", roomCount);
            list = initRoom();
            model.addAttribute("rooms", list);
        }

        return "room/showRoom";
    }

    @RequestMapping(value = "/bookRoom", method = RequestMethod.POST)
    @ResponseBody
    public boolean bookRoom(@RequestBody BookRoomVO bookRoomVO, Model model) {
        List<RoomVO> roomVOS = (List<RoomVO>) request.getSession().getAttribute("rooms");
        LOGGER.info("booking param: " + bookRoomVO);
        RoomVO roomVO = roomVOS.get(bookRoomVO.getNo() - 1);
        boolean result = booking(roomVO, bookRoomVO);
        LOGGER.info("booking room: " + result);
        Collections.sort(roomVO.getList(), (o1, o2) -> o1.getBeginDate() > o2.getBeginDate() ? 1 : -1);
        return result;
    }

    /**
     * 初始化 会议室
     *
     * @return
     */
    private List<RoomVO> initRoom() {
        List<RoomVO> list = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(i + 1);
            roomVO.setName("room " + (i + 1));
            roomVO.setList(Lists.newArrayList(new RoomPair(0, 24)));
            list.add(roomVO);
        }
        return list;
    }

    /**
     * 判断是否可以进行预定
     *
     * @return
     */
    private boolean booking(RoomVO roomVO, BookRoomVO bookRoomVO) {
        List<RoomPair> pairs = roomVO.getList();
        RoomPair findPair = null;
        for (RoomPair pair : pairs) {
            if (pair.getBeginDate() <= bookRoomVO.getBeginDate() && pair.getEndDate() >= bookRoomVO.getEndDate()) {
                // 进行切割
                findPair = pair;
                pairs.remove(pair);
                break;
            }
        }

        if (findPair != null) {
            int tempBeginDate1 = bookRoomVO.getBeginDate() - findPair.getBeginDate();
            if (tempBeginDate1 > 0) {
                roomVO.getList().add(new RoomPair(findPair.getBeginDate(), bookRoomVO.getBeginDate()));
            }

            int tempBeginDate2 = findPair.getEndDate() - bookRoomVO.getEndDate();
            if (tempBeginDate2 > 0) {
                roomVO.getList().add(new RoomPair(bookRoomVO.getEndDate(), findPair.getEndDate()));
            }
            return true;
        }

        return false;
    }

}
