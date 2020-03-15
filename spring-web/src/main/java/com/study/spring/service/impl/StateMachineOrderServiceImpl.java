package com.study.spring.service.impl;

import com.study.spring.entity.StateMachineOrder;
import com.study.spring.mapper.StateMachineOrderDao;
import com.study.spring.service.StateMachineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
@Service
public class StateMachineOrderServiceImpl implements StateMachineOrderService {

    @Autowired
    private StateMachineOrderDao stateMachineOrderDao;


    @Override
    public Long saveStateMachineOrder(StateMachineOrder stateMachineOrder) {
        return stateMachineOrderDao.saveStateMachineOrder(stateMachineOrder);
    }

    @Override
    public StateMachineOrder getStateMachineOrderByOrderId(String orderId) {
        return stateMachineOrderDao.getStateMachineOrderByOrderId(orderId);
    }

    @Override
    public long updateStateMachineOrderStatusByOrderId(String orderId, String status) {
        return stateMachineOrderDao.updateStateMachineOrderStatusByOrderId(orderId, status);
    }
}
