package com.study.model;

import com.study.base.ToString;

public class ServiceScene extends ToString {

    private Long Id;

    private String BizScene;

    private String ServiceId;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBizScene() {
        return BizScene;
    }

    public void setBizScene(String bizScene) {
        BizScene = bizScene;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }
}
