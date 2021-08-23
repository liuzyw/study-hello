package com.study.service;

import com.alibaba.fastjson.JSON;
import com.study.dao.ServiceSceneDao;
import com.study.dao.StateOrderDao;
import com.study.entity.StateRequest;
import com.study.entity.StateResult;
import com.study.model.ServiceScene;
import com.study.model.StateConfig;
import com.study.model.StateNode;
import com.study.model.StateOrder;
import com.study.util.IdNoUtil;
import com.study.util.StateConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateOrderDao stateOrderDao;

    @Autowired
    private ServiceSceneDao serviceSceneDao;

    @Autowired
    private RuleService ruleService;


    @Override
    public StateResult initState(StateRequest request) {

        StateOrder stateOrder = stateOrderDao.getStateOrderByOutOrderId(request.getOutOrderId(), request.getBizScene());
        if (stateOrder != null) {
            System.out.println("订单重复");
        }

        ServiceScene serviceScene = serviceSceneDao.getLatestServiceScene(request.getServiceId());


        stateOrder = new StateOrder();
        stateOrder.setStateOrderId(IdNoUtil.getStateOrderId());


        Long aLong = stateOrderDao.saveStateOrder(stateOrder);


        return null;
    }

    @Override
    public StateResult pushState(StateRequest request) {
        StateOrder stateOrder = stateOrderDao.getStateOrderByOutOrderId(request.getOutOrderId(), request.getBizScene());
        if (stateOrder == null) {
            System.out.println("订单不存在");
        }

        ServiceScene serviceScene = serviceSceneDao.getServiceScene(stateOrder.getBizScene(), stateOrder.getVersion());

        StateConfig stateConfig = JSON.parseObject(serviceScene.getStateConfig(), StateConfig.class);

        StateNode currentNode = StateConfigUtil.getCurrentNode(stateConfig, stateOrder.getStatus());

        StateNode toNode = null;

        for (String child : currentNode.getChilds()) {
            if (child.equals(request.getToState())) {
                toNode = StateConfigUtil.getCurrentNode(stateConfig, request.getToState());
                break;
            }
        }

        if (toNode == null) {
            System.out.println("推进状态错误");
        }


        boolean executeRule = ruleService.executeRule(toNode.getRuleId(), request.getParams());
        if (executeRule) {
            stateOrderDao.updateStateOrderStatus(stateOrder.getStateOrderId(), stateOrder.getStatus(), request.getToState());
        } else {
            System.out.println("推进到目标状态规则校验失败");
        }

        return new StateResult(true);
    }
}
