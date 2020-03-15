package com.study.spring.state.actionitem;

import com.study.spring.service.StateMachineOrderService;
import com.study.spring.state.action.ActionItem;
import com.study.spring.state.constant.StateConstant;
import com.study.spring.state.enums.OrderStatusEnum;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class WaitOrder implements ActionItem<String> {

    private static final Long serialVersionUID = 1L;

    @Autowired
    private StateMachineOrderService stateMachineOrderService;


    @Override
    public String doBiz(Map<String, Object> input) {

        System.out.println("UpdateOrder ......");

        stateMachineOrderService.updateStateMachineOrderStatusByOrderId((String) input.get(StateConstant.ORDER_ID), OrderStatusEnum.WAIT.code);

        return input.get(StateConstant.ORDER_ID).toString();
    }
}
