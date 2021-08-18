package com.study.service;

import com.study.dao.ServiceSceneDao;
import com.study.dao.StateOrderDao;
import com.study.entity.StateRequest;
import com.study.entity.StateResult;
import com.study.model.ServiceScene;
import com.study.model.StateOrder;
import com.study.util.IdNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateOrderDao stateOrderDao;

    @Autowired
    private ServiceSceneDao serviceSceneDao;


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
        return null;
    }
}
