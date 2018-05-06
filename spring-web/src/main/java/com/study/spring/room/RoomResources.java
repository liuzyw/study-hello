package com.study.spring.room;

import com.google.common.collect.Lists;
import com.study.spring.vo.RoomPair;
import com.study.spring.vo.RoomVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018-05-06
 *
 * @author liuzhaoyuan
 */
public class RoomResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomResources.class);

    private static int roomCount = 3;

    private static RoomQueue queue;


    private RoomResources() {
    }

    /**
     * 可展示的会议室列表
     */
    private static List<RoomVO> list;

//    /**
//     * 已预订的会议室时间段
//     */
//    private static List<RoomPair> bookRoomPair;


    /**
     * 获取调度队列
     *
     * @return
     */
    public synchronized static RoomQueue getRoomQueue() {
        if (queue == null) {
            queue = new RoomQueue(5);
        }

        return queue;
    }

    /**
     * 获取会议室列表
     *
     * @return
     */
    public synchronized static List<RoomVO> getRoomList() {
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.info("init room count:{}", roomCount);
            list = initRoom();
        }

        return list;
    }

    /**
     * 初始化 会议室
     *
     * @return
     */
    private static List<RoomVO> initRoom() {
        List<RoomVO> list = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(i + 1);
            roomVO.setName("room " + (i + 1));
            roomVO.setList(Lists.newArrayList(new RoomPair(0, 24)));
            roomVO.setBookRooms(Lists.newArrayList());
            list.add(roomVO);
        }
        return list;
    }

    /**
     * 判断是否可以进行预定
     *
     * @return
     */
    public static boolean booking(RoomPair roomPair) {
        RoomVO roomVO = list.get(roomPair.getNo() - 1);
        // 加锁
        synchronized (roomVO) {
            List<RoomPair> pairs = roomVO.getList();
            RoomPair findPair = null;
            for (RoomPair pair : pairs) {
                if (pair.getBeginDate() <= roomPair.getBeginDate() && pair.getEndDate() >= roomPair.getEndDate()) {
                    // 进行切割
                    findPair = pair;
                    pairs.remove(pair);
                    break;
                }
            }

            if (findPair != null) {
                // 添加到 队列中
                boolean re = appendToQueue(roomPair);
                if (!re) {
                    LOGGER.info("不在规定时间范围");
                    return false;
                }
                // 添加到已预订的队列中
                roomVO.getBookRooms().add(roomPair);

                // 修改空闲队列
                int tempBeginDate1 = roomPair.getBeginDate() - findPair.getBeginDate();
                if (tempBeginDate1 > 0) {
                    roomVO.getList().add(new RoomPair(findPair.getBeginDate(), roomPair.getBeginDate()));
                }

                int tempBeginDate2 = findPair.getEndDate() - roomPair.getEndDate();
                if (tempBeginDate2 > 0) {
                    roomVO.getList().add(new RoomPair(roomPair.getEndDate(), findPair.getEndDate()));
                }
                Collections.sort(roomVO.getList(), (o1, o2) -> o1.getBeginDate() > o2.getBeginDate() ? 1 : -1);
                return true;
            }

            return false;
        }
    }

    /**
     * 添加到队列上
     */
    private static boolean appendToQueue(RoomPair pair) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = localDateTime.getHour();
        int begin = pair.getBeginDate() - hour;
        if (begin < queue.getCapacity()) {
            int find = begin + queue.getIndex();
            int index = find % queue.getCapacity();
            queue.appendToQueue(index, pair);
            return true;
        }

        return false;
    }


    /**
     * 签到
     *
     * @return
     */
    public static boolean signIn(RoomPair roomPair) {
        List<RoomPair> bookRooms = list.get(roomPair.getNo() - 1).getBookRooms();
        if (CollectionUtils.isNotEmpty(bookRooms)) {
            for (RoomPair pair : bookRooms) {
                if (pair.getBookName().equals(roomPair.getBookName())) {
                    bookRooms.remove(roomPair);

                    List<RoomPair> rooms = queue.getBookRoomPair();
                    outer:
                    for (RoomPair room : rooms) {
                        if (room.getBookName().equals(roomPair.getBookName()) && room.getNo().equals(roomPair.getNo())
                            && room.getBeginDate().equals(roomPair.getBeginDate())) {
                            rooms.remove(room);
                            break outer;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 释放会议室
     */
    public static void releaseRoom(List<RoomPair> roomPairs) {
        for (RoomPair pair : roomPairs) {
            RoomVO roomVO = list.get(pair.getNo() - 1);
            synchronized (roomVO) {
                List<RoomPair> freeRooms = roomVO.getList();
                if (CollectionUtils.isNotEmpty(freeRooms)) {
                    RoomPair first = freeRooms.get(0);
                    if (first.getBeginDate() > pair.getEndDate()) {
                        freeRooms.add(pair);
                    } else if (first.getBeginDate() == pair.getEndDate()) {
                        first.setBeginDate(pair.getBeginDate());
                    } else {
                        int p = 0;
                        outer:
                        for (RoomPair free : freeRooms) {
                            if (free.getEndDate() == pair.getBeginDate()) {
                                if (freeRooms.size() - 1 > p) {
                                    if (freeRooms.get(p + 1).getBeginDate() > pair.getEndDate()) {
                                        free.setEndDate(pair.getEndDate());
                                    } else if (freeRooms.get(1).getBeginDate() == pair.getEndDate()) {
                                        freeRooms.get(p + 1).setBeginDate(free.getBeginDate());
                                        freeRooms.remove(p);
                                    }
                                } else {
                                    free.setEndDate(pair.getEndDate());
                                }
                                break outer;
                            } else if (free.getEndDate() > pair.getBeginDate()) {

                                if (freeRooms.size() - 1 > p) {
                                    if (freeRooms.get(p + 1).getBeginDate() > pair.getEndDate()) {
                                        freeRooms.add(pair);
                                    } else if (freeRooms.get(1).getBeginDate() == pair.getEndDate()) {
                                        freeRooms.get(p + 1).setBeginDate(pair.getBeginDate());
                                    }
                                } else {
                                    freeRooms.add(pair);
                                }
                                break outer;
                            }
                        }
                    }

                } else {
                    freeRooms.add(pair);
                }
            }
        }
    }


}
