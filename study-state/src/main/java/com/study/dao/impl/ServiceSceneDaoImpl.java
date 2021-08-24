package com.study.dao.impl;

import com.alibaba.fastjson.JSON;
import com.study.constants.Constant;
import com.study.dao.ServiceSceneDao;
import com.study.model.ServiceScene;
import com.study.model.StateConfig;
import com.study.model.StateNode;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ServiceSceneDaoImpl implements ServiceSceneDao {

    private static Map<String, ServiceScene> serviceSceneMap = new HashMap<>();

    static {

        ServiceScene serviceScene = new ServiceScene();
        serviceScene.setId(1L);
        serviceScene.setBizScene("COUPON");
        serviceScene.setServiceId("SID000001");
        serviceScene.setStatus("VALID");
        serviceScene.setVersion(1);

        StateConfig stateConfig = new StateConfig();
        List<StateNode> list = new ArrayList<>();
        stateConfig.setStateNodes(list);

        StateNode stateNode3 = new StateNode();
        stateNode3.setOrder(3);
        stateNode3.setStateName(Constant.REFUND);
        stateNode3.setRuleId("RUID0000003");
        list.add(stateNode3);

        StateNode stateNode4 = new StateNode();
        stateNode4.setOrder(4);
        stateNode4.setStateName(Constant.CLOSED);
        stateNode4.setRuleId("RUID0000004");
        list.add(stateNode4);


        StateNode stateNode1 = new StateNode();
        stateNode1.setOrder(1);
        stateNode1.setStateName(Constant.INIT);
        stateNode1.setChilds(Arrays.asList(Constant.USED, Constant.CLOSED));
        stateNode1.setRuleId("RUID0000001");
        list.add(stateNode1);


        StateNode stateNode2 = new StateNode();
        stateNode2.setOrder(2);
        stateNode2.setStateName(Constant.USED);
        stateNode2.setChilds(Arrays.asList(Constant.REFUND));
        stateNode2.setRuleId("RUID0000002");
        list.add(stateNode2);

        serviceScene.setStateConfig(JSON.toJSONString(stateConfig));

        serviceSceneMap.put("SID000001", serviceScene);
    }

    @Override
    public ServiceScene getLatestServiceScene(String serviceId) {
        return serviceSceneMap.get(serviceId);
    }


    @Override
    public ServiceScene getServiceScene(String serviceId, Integer version) {
        return serviceSceneMap.get(serviceId);
    }
}
