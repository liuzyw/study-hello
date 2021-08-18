package com.study.dao;

import com.study.model.StateOrder;

public interface StateOrderDao {

    StateOrder getStateOrderByOutOrderId(String outOrderId, String bizScene);

    StateOrder getStateOrder(String stateOrderId);

    Long updateStateOrderStatus(String stateOrderId, String fromStatus, String toStatus);

    Long saveStateOrder(StateOrder stateOrder);

}
