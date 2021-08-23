package com.study.util;

import com.study.constants.Constant;
import com.study.model.StateConfig;
import com.study.model.StateNode;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateConfigUtilTest extends TestCase {

    public void testGetCurrentNode() {
        StateConfig stateConfig = new StateConfig();
        List<StateNode> list = new ArrayList<>();
        stateConfig.setStateNodes(list);

        StateNode stateNode3 = new StateNode();
        stateNode3.setOrder(3);
        stateNode3.setStateName(Constant.REFUND);
        stateNode3.setRuleId("RUID0000001");
        list.add(stateNode3);

        StateNode stateNode4 = new StateNode();
        stateNode4.setOrder(4);
        stateNode4.setStateName(Constant.CLOSED);
        stateNode4.setRuleId("RUID0000001");
        list.add(stateNode4);


        StateNode stateNode1 = new StateNode();
        stateNode1.setOrder(1);
        stateNode1.setStateName(Constant.INIT);
        stateNode1.setChilds(Arrays.asList(Constant.USED, Constant.CLOSED));
        stateNode1.setRuleId("RUID0000001");
        list.add(stateNode1);


        StateNode stateNode2 = new StateNode();
        stateNode2.setOrder(2);
        stateNode2.setStateName(Constant.USED);
        stateNode2.setChilds(Arrays.asList(Constant.REFUND));
        stateNode2.setRuleId("RUID0000001");
        list.add(stateNode2);

        StateNode currentNode = StateConfigUtil.getCurrentNode(stateConfig, Constant.CLOSED);

        System.out.println(currentNode);

    }
}