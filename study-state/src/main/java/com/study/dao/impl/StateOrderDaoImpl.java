package com.study.dao.impl;

import com.study.dao.StateOrderDao;
import com.study.model.StateOrder;
import org.springframework.stereotype.Service;


@Service
public class StateOrderDaoImpl implements StateOrderDao {


    @Override
    public StateOrder getStateOrderByOutOrderId(String outOrderId, String bizScene) {
        return null;
    }

    @Override
    public StateOrder getStateOrder(String stateOrderId) {
        return null;
    }

    @Override
    public Long updateStateOrderStatus(String stateOrderId, String fromStatus, String toStatus) {
        return null;
    }

    @Override
    public Long saveStateOrder(StateOrder stateOrder) {
        return null;
    }
}
