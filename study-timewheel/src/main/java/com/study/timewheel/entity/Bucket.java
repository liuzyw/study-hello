package com.study.timewheel.entity;

public class Bucket {

    private TimeTaskList timeTaskList;

    public Bucket() {
        this.timeTaskList = new TimeTaskList();
    }

    public Bucket(TimeTaskList list) {
        if (list == null) {
            list = new TimeTaskList();
        }
        this.timeTaskList = list;
    }

    public TimeTaskList getList() {
        return this.timeTaskList;
    }

    public void setList(TimeTaskList list) {
        this.timeTaskList = list;
    }


}
