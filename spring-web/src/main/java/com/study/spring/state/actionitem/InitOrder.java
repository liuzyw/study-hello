package com.study.spring.state.actionitem;

import com.study.spring.entity.StateMachineOrder;
import com.study.spring.service.StateMachineOrderService;
import com.study.spring.state.action.ActionItem;
import com.study.spring.state.enums.OrderStatusEnum;
import com.study.spring.state.model.StateHelper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class InitOrder implements ActionItem<String> {

    private static final Long serialVersionUID = 1L;

    @Autowired
    private StateMachineOrderService stateMachineOrderService;


    @Override
    public String doBiz(Map<String, Object> input) {

        System.out.println("initOrder ......");

        StateMachineOrder order = new StateMachineOrder();

        String orderId = StateHelper.generateOrderId();

        order.setOrderId(orderId);
        order.setStatus(OrderStatusEnum.INIT.code);
        order.setBizCategory("RECEIPT");
        stateMachineOrderService.saveStateMachineOrder(order);

        return orderId;
    }
}
