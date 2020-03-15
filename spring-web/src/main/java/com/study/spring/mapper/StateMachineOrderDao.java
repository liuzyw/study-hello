package com.study.spring.mapper;

import com.study.spring.entity.StateMachineOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
@Repository
public interface StateMachineOrderDao {

    Long saveStateMachineOrder(StateMachineOrder stateMachineOrder);

    StateMachineOrder getStateMachineOrderByOrderId(String orderId);

    long updateStateMachineOrderStatusByOrderId(@Param("orderId") String orderId, @Param("status") String status);

}
