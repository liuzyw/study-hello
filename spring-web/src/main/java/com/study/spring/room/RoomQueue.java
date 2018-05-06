package com.study.spring.room;

import com.study.spring.vo.RoomPair;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018-05-06
 *
 * @author liuzhaoyuan
 */
public class RoomQueue {

    private RoomQueueNode[] queue;
    private int size;
    private int capacity;
    private int index;
    /**
     * 已预订的会议室时间段
     */
    private List<RoomPair> bookRoomPair = new ArrayList<>();


    public RoomQueue(int capacity) {
        this.index = 0;
        this.size = 0;
        this.capacity = capacity;
        this.queue = new RoomQueueNode[capacity];
        for (int i = 0; i < capacity; i++) {
            queue[i] = new RoomQueueNode();
        }
    }


    public class RoomQueueNode {

        /**
         * 每个槽有个对象锁, 用于遍历和添加操作
         */
        private Object lock = new Object();

        private List<RoomPair> list = new ArrayList<>();

        public List<RoomPair> getList() {
            return list;
        }

        public Object getLock() {
            return lock;
        }
    }

    public RoomQueueNode[] getQueue() {
        return queue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    /**
     * 添加到队列中
     *
     * @return
     */
    public boolean appendToQueue(int index, RoomPair pair) {
        RoomQueueNode node = queue[index];
        synchronized (node.getLock()) {
            node.getList().add(pair);
        }
        return true;
    }

    public List<RoomPair> getBookRoomPair() {
        return bookRoomPair;
    }

    public void setBookRoomPair(List<RoomPair> bookRoomPair) {
        this.bookRoomPair = bookRoomPair;
    }
}


