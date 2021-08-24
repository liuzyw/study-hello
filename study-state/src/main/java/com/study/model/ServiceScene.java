package com.study.model;

import com.study.base.ToString;

public class ServiceScene extends ToString {

    private Long id;

    private String bizScene;

    private String serviceId;

    private Integer version;

    private String status;

    private String stateConfig;

    public ServiceScene() {
    }

    public ServiceScene(Long id, String bizScene, String serviceId, Integer version, String status, String stateConfig) {
        this.id = id;
        this.bizScene = bizScene;
        this.serviceId = serviceId;
        this.version = version;
        this.status = status;
        this.stateConfig = stateConfig;
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

    public String getStateConfig() {
        return stateConfig;
    }

    public void setStateConfig(String stateConfig) {
        this.stateConfig = stateConfig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizScene() {
        return bizScene;
    }

    public void setBizScene(String bizScene) {
        this.bizScene = bizScene;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
