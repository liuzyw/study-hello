package com.study.spring.vo;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */

public class RoomPair {

    private int beginDate;
    private int endDate;
    /**
     * 是否使用完毕
     */
    private boolean free = false;

    public RoomPair() {
    }

    public RoomPair(int beginDate, int endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(int beginDate) {
        this.beginDate = beginDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "RoomPair{" +
            "beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", free=" + free +
            '}';
    }
}
