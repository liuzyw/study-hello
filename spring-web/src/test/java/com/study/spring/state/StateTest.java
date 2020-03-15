package com.study.spring.state;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.study.spring.entity.StateMachineOrder;
import com.study.spring.service.StateMachineConfigService;
import com.study.spring.service.StateMachineOrderService;
import com.study.spring.state.action.ActionItem;
import com.study.spring.state.action.ActionItemDefinition;
import com.study.spring.state.action.ActionItemRegistry;
import com.study.spring.state.constant.StateConstant;
import com.study.spring.state.enums.OrderStatusEnum;
import com.study.spring.state.enums.StateEventEnum;
import com.study.spring.state.model.ActionConditionDefinition;
import com.study.spring.state.model.ConditionDefinition;
import com.study.spring.state.model.RuleDefinition;
import com.study.spring.state.model.StateConditionDefinition;
import com.study.spring.state.model.StateHelper;
import com.study.spring.state.model.StateMachineConfigDefinition;
import com.study.spring.state.model.StateNode;
import com.study.spring.state.model.StateNodeDefinition;
import com.study.spring.state.runtime.StateMachine;
import com.study.spring.state.runtime.StateMachineContext;
import com.study.spring.state.runtime.StateNodeFetcher;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class StateTest {

    private static final Long serialVersionUID = 1L;


    @Autowired
    private ActionItemRegistry actionItemRegistry;

    @Autowired
    private StateMachineConfigService stateMachineConfigService;

    @Autowired
    private StateMachineOrderService stateMachineOrderService;


    @Autowired
    private StateNodeFetcher stateNodeFetcher;

    @Autowired
    private StateMachine stateMachine;

    private static final String RECEIPT = "RECEIPT";

    @Test
    public void testActionItemRegistry() {
        ActionItem initOrder = actionItemRegistry.getActionItemByCode("initOrder");
        Object o = initOrder.doBiz(null);
        Assert.assertNotNull(o);

    }

    @Test
    public void testDao() {
        StateMachineConfigDefinition receipt = stateMachineConfigService.getStateMachineConfigByBizCategory("RECEIPT");

        System.out.println(receipt);

        StateMachineOrder order = new StateMachineOrder();

        String orderId = StateHelper.generateOrderId();

        order.setOrderId(orderId);
        order.setStatus(OrderStatusEnum.INIT.code);
        order.setBizCategory(RECEIPT);
        stateMachineOrderService.saveStateMachineOrder(order);

        StateMachineOrder o = stateMachineOrderService.getStateMachineOrderByOrderId(orderId);

        System.out.println(o);

    }


    @Test
    public void StateNodeFetcher() {
        StateNode stateNode = stateNodeFetcher.startNode(RECEIPT);
        System.out.println(JSON.toJSONString(stateNode));

        StateNode stateNode1 = stateNodeFetcher.fetchStateNode(RECEIPT, "WAIT");
        System.out.println(JSON.toJSONString(stateNode1));

        stateNode1 = stateNodeFetcher.fetchStateNode(RECEIPT, "CLOSE");
        System.out.println(JSON.toJSONString(stateNode1));

        stateNode1 = stateNodeFetcher.fetchStateNode(RECEIPT, "FINISH");
        System.out.println(JSON.toJSONString(stateNode1));
    }

    @Test
    public void test_push_1() {
        // init

        Map<String, Object> map = new HashMap<>();
        map.put(StateConstant.BIZ_CATEGORY, RECEIPT);
        map.put(StateConstant.PUSH_EVENT, StateEventEnum.EVT_INIT.code);

        StateMachineContext machineContext = stateMachine.init(RECEIPT, map);

        System.out.println(JSON.toJSONString(machineContext));

    }

    @Test
    public void test_push_2() {
        // init

        Map<String, Object> map = new HashMap<>();
        map.put(StateConstant.BIZ_CATEGORY, RECEIPT);
        map.put(StateConstant.PUSH_EVENT, StateEventEnum.EVT_FINISH.code);
        map.put(StateConstant.ORDER_ID, "SO20200315716296");

        StateMachineContext machineContext = stateMachine.push(map);

        System.out.println(JSON.toJSONString(machineContext));

    }


    public static void main(String[] args) throws Exception {
        StateMachineConfigDefinition a1 = new StateMachineConfigDefinition();
        a1.setBizCategory("RECEIPT");
        StateNodeDefinition stateNodeDefinitiona1 = new StateNodeDefinition();
        a1.setStateNodeDefinition(stateNodeDefinitiona1);
        stateNodeDefinitiona1.setStart(true);
        stateNodeDefinitiona1.setStartCode("INIT");

        ActionConditionDefinition actionConditionDefinitiona11 = new ActionConditionDefinition();
        stateNodeDefinitiona1.setActionConditionDefinitions(Lists.newArrayList(actionConditionDefinitiona11));
        stateNodeDefinitiona1.setActionConditionDefinitions(Lists.newArrayList(actionConditionDefinitiona11));

        ActionItemDefinition actionItemDefinitiona11 = new ActionItemDefinition();
        actionItemDefinitiona11.setOrder(1);
        actionItemDefinitiona11.setCode("initOrder");
        actionItemDefinitiona11.setDesc("initOrder");
        actionConditionDefinitiona11.setActionItemDefinitions(Lists.newArrayList(actionItemDefinitiona11));

        ConditionDefinition conditionDefinitiona11 = new ConditionDefinition();
        conditionDefinitiona11.setJexlExpression("R0");
        conditionDefinitiona11.setRuleDefinitions(Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_INIT", "=")));

        actionConditionDefinitiona11.setConditionDefinition(conditionDefinitiona11);

        StateConditionDefinition stateConditionDefinition1 = new StateConditionDefinition();
        StateConditionDefinition stateConditionDefinition2 = new StateConditionDefinition();
        stateNodeDefinitiona1.setStateConditionDefinitions(Lists.newArrayList(stateConditionDefinition1, stateConditionDefinition2));

        stateConditionDefinition1.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_CLOSE", "="))));

        StateNodeDefinition stateNodeDefinitionb1 = new StateNodeDefinition();
        stateConditionDefinition1.setStateNodeDefinition(stateNodeDefinitionb1);
        stateNodeDefinitionb1.setStartCode("CLOSE");
        stateNodeDefinitionb1.setStart(false);
        ActionConditionDefinition actionConditionDefinitionb1 = new ActionConditionDefinition();
        stateNodeDefinitionb1.setActionConditionDefinitions(Lists.newArrayList(actionConditionDefinitionb1));
        actionConditionDefinitionb1.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_CLOSE", "="))));
        actionConditionDefinitionb1.setActionItemDefinitions(Lists.newArrayList(new ActionItemDefinition(1, "closeOrder", "closeOrder")));

        stateConditionDefinition2.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_WAIT", "="))));

        StateNodeDefinition stateNodeDefinitionC1 = new StateNodeDefinition();
        stateConditionDefinition2.setStateNodeDefinition(stateNodeDefinitionC1);
        stateNodeDefinitionC1.setStart(false);
        stateNodeDefinitionC1.setStartCode("WAIT");
        ActionConditionDefinition actionConditionDefinitionc1 = new ActionConditionDefinition();

        stateNodeDefinitionC1.setActionConditionDefinitions(Lists.newArrayList(actionConditionDefinitionc1));
        actionConditionDefinitionc1.setActionItemDefinitions(Lists.newArrayList(new ActionItemDefinition(1, "waitOrder", "waitOrder")));
        actionConditionDefinitionc1.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_WAIT", "="))));

        StateConditionDefinition stateConditionDefinitiond1 = new StateConditionDefinition();
        stateNodeDefinitionC1.setStateConditionDefinitions(Lists.newArrayList(stateConditionDefinitiond1));
        stateConditionDefinitiond1.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_FINISH", "="))));

        StateNodeDefinition stateNodeDefinitiond1 = new StateNodeDefinition();
        stateConditionDefinitiond1.setStateNodeDefinition(stateNodeDefinitiond1);
        stateNodeDefinitiond1.setStartCode("FINISH");
        stateNodeDefinitiond1.setStart(false);
        ActionConditionDefinition actionConditionDefinitiond1 = new ActionConditionDefinition();
        stateNodeDefinitiond1.setActionConditionDefinitions(Lists.newArrayList(actionConditionDefinitiond1));
        actionConditionDefinitiond1.setConditionDefinition(new ConditionDefinition("R0", Lists.newArrayList(new RuleDefinition("R0", "String", "push_event", "EVT_FINISH", "="))));
        actionConditionDefinitiond1.setActionItemDefinitions(Lists.newArrayList(new ActionItemDefinition(1, "finishOrder", "finishOrder")));

        String json = JSON.toJSONString(a1);
        System.out.println(json);

        StateMachineConfigDefinition content = JSONObject.parseObject(json, StateMachineConfigDefinition.class);

        System.out.println(content);
    }


}
