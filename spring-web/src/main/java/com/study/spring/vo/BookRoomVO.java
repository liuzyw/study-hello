package com.study.spring.vo;

import java.io.Serializable;

/**
 * Created on 2018-05-06
 *
 * @author liuzhaoyuan
 */
public class BookRoomVO implements Serializable {

    private static final long serialVersionUID = -4277421070178513563L;

    private Integer no;
    private Integer beginDate;
    private Integer endDate;

    public BookRoomVO() {
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Integer beginDate) {
        this.beginDate = beginDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BookRoomVO{" +
            "no=" + no +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            '}';
    }
}
