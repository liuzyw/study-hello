package com.study.model;

import com.study.base.ToString;

public class StateOrder extends ToString {

    private Long id;

    private String stateOrderId;

    private String outOrderId;

    private String bizScene;

    private Integer version;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateOrderId() {
        return stateOrderId;
    }

    public void setStateOrderId(String stateOrderId) {
        this.stateOrderId = stateOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getBizScene() {
        return bizScene;
    }

    public void setBizScene(String bizScene) {
        this.bizScene = bizScene;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
