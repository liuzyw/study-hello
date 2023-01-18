package com.study.timewheel.entity;


import java.time.LocalDateTime;


public class TimeTaskEntry {
    private Long id;
    private String data;
    private int delayTime;

    private TimeTaskEntry prefix;
    private TimeTaskEntry next;


    public TimeTaskEntry() {
    }

    public TimeTaskEntry(Long id, String data, int delayTime) {
        this.id = id;
        this.data = data;
        this.delayTime = delayTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public TimeTaskEntry getPrefix() {
        return prefix;
    }

    public void setPrefix(TimeTaskEntry prefix) {
        this.prefix = prefix;
    }

    public TimeTaskEntry getNext() {
        return next;
    }

    public void setNext(TimeTaskEntry next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "TimeTaskEntry{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}



