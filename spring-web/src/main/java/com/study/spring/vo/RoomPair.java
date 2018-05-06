package com.study.spring.vo;

import lombok.Data;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */
@Data
public class RoomPair {

    /**
     * 预订人
     */
    private String bookName;

    /**
     * 会议室编号
     */
    private Integer no;

    /**
     * 预订开始时间
     */
    private Integer beginDate;

    /**
     * 预订结束时间
     */
    private Integer endDate;
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

}
