package com.study.dao;

import com.study.model.ServiceScene;

public interface ServiceSceneDao {

    ServiceScene getLatestServiceScene(String serviceId);

    ServiceScene getServiceScene(String serviceId, Integer version);


}
