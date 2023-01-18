package com.study.timewheel.entity;

import org.junit.Test;

import java.time.LocalDateTime;

public class TimeTaskListTest {


    @Test
    public void TestTimeTaskListTest0() {
        TimeTaskList list = new TimeTaskList();
        list.put(new TimeTaskEntry(1L, "1a", 1));
        list.put(new TimeTaskEntry(2L, "2a", 2));
        list.put(new TimeTaskEntry(3L, "3a", 3));
        list.printf();


        System.out.println(list.get().getId());
        System.out.println(list.getSize());
        list.printf();
        list.put(new TimeTaskEntry(4L, "4a", 4));
        list.printf();
        System.out.println(list.get().getId());
        System.out.println(list.getSize());
        System.out.println(list.get().getId());
        System.out.println(list.getSize());
        System.out.println(list.get().getId());
        System.out.println(list.getSize());
        System.out.println(list.get());

    }


    @Test
    public void TestTimeWheel() throws InterruptedException {
        System.out.println("开始测试");
        TimeWheel timeWheel = new TimeWheel(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "8", 8));

                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "9", 9));


                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "5", 5));
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "7", 7));
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "7", 7));

                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "6", 6));
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "6", 6));
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "6", 6));


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "18", 8));

                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "19", 9));


                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "15", 5));
                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "17", 7));

                timeWheel.addTaskEntry(new TimeTaskEntry(1L, "16", 6));


            }
        }).start();

        Thread.sleep(1000000000);

    }


}