package com.study.spring.job;

import com.study.spring.room.RoomQueue;
import com.study.spring.room.RoomQueue.RoomQueueNode;
import com.study.spring.room.RoomResources;
import com.study.spring.vo.RoomPair;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created on 2018-05-06
 *
 * <p>会议室调度任务</p>
 *
 * @author liuzhaoyuan
 */
public class RoomSchedulerJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomSchedulerJob.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static int index = 0;

    private static final ReentrantLock lock = new ReentrantLock();


    /**
     * 进行任务调度
     */
    public void schedulerRoom() {
        RoomQueue queue = RoomResources.getRoomQueue();
        LOGGER.info("RoomSchedulerJob run: " + LocalDateTime.now());

        lock.lock();
        try {
            int capacity = queue.getCapacity();
            if (index == (capacity - 1)) {
                index = 0;
            } else {
                index++;
            }
            RoomQueueNode node = queue.getQueue()[index];
            final List<RoomPair> list = node.getList();
            queue.setBookRoomPair(list);

            threadPoolTaskExecutor.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (CollectionUtils.isNotEmpty(list)) {
                    RoomResources.releaseRoom(list);
                }
            });

        } finally {
            lock.unlock();
        }

    }

}
