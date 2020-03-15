package com.study.spring.service;

import com.study.spring.entity.StateMachineOrder;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public interface StateMachineOrderService {


    Long saveStateMachineOrder(StateMachineOrder stateMachineOrder);

    StateMachineOrder getStateMachineOrderByOrderId(String orderId);

    long updateStateMachineOrderStatusByOrderId(String orderId, String status);


}
