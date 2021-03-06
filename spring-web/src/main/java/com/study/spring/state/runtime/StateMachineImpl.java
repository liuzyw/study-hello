package com.study.spring.state.runtime;

import com.study.spring.entity.StateMachineOrder;
import com.study.spring.service.StateMachineOrderService;
import com.study.spring.state.action.ActionResult;
import com.study.spring.state.constant.StateConstant;
import com.study.spring.state.model.StateNode;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateMachineImpl implements StateMachine {

    @Autowired
    private StateNodeFetcher stateNodeFetcher;

    @Autowired
    private StateNodeExecutor stateNodeExecutor;


    @Autowired
    private ActionConditionExecutor actionConditionExecutor;

    @Autowired
    private StateMachineOrderService stateMachineOrderService;

    @Override
    public StateMachineContext init(String bizCategory, Map<String, Object> input) {

        StateMachineContext machineContext = new StateMachineContext();
        machineContext.setInput(input);

        StateNode targetNode = stateNodeFetcher.startNode(bizCategory);

        pushFromSourceToTarget(null, targetNode, input, machineContext);

        return machineContext;
    }


    @Override
    public StateMachineContext push(Map<String, Object> input) {

        StateMachineOrder stateMachineOrder = stateMachineOrderService.getStateMachineOrderByOrderId(input.get(StateConstant.ORDER_ID).toString());

        StateNode sourceNode = stateNodeFetcher.fetchStateNode("RECEIPT", stateMachineOrder.getStatus());

        StateMachineContext machineContext = new StateMachineContext();
        machineContext.setInput(input);

        StateNode targetNode = sourceNode.nextNode(input);

        pushFromSourceToTarget(sourceNode, targetNode, input, machineContext);

        return machineContext;
    }


    private void pushFromSourceToTarget(StateNode sourceNode, StateNode targetNode, Map<String, Object> input, StateMachineContext machineContext) {

        machineContext.setSourceNode(sourceNode);
        machineContext.setTargetNode(targetNode);

        ActionResult actionResult = stateNodeExecutor.executeNode(targetNode, input);

        machineContext.setActionResult(actionResult);
    }
}
