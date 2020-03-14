package com.study.spring.entity;

import com.study.spring.base.ToString;
import java.util.Date;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateMachineOrder extends ToString {


    private long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String orderId;

    private String status;

    private String bizCategory;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }
}
