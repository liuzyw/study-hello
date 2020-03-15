package com.study.spring.state.runtime;

import com.study.spring.service.StateMachineConfigService;
import com.study.spring.state.model.StateHelper;
import com.study.spring.state.model.StateMachineConfigDefinition;
import com.study.spring.state.model.StateNode;
import com.study.spring.state.model.StateScene;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public class StateNodeFetcherImpl implements StateNodeFetcher {

    @Autowired
    private StateMachineConfigService stateMachineConfigService;


    @Override
    public StateNode startNode(String bizCategory) {

        StateMachineConfigDefinition stateMachineConfigDefinition = stateMachineConfigService.getStateMachineConfigByBizCategory(bizCategory);

        StateScene stateScene = StateHelper.generate(stateMachineConfigDefinition);

        return stateScene.getStateNode();
    }


    @Override
    public StateNode fetchStateNode(String bizCategory, String currentStatus) {

        StateNode stateNode = startNode(bizCategory);

        if (stateNode.getStartCode().equals(currentStatus)) {
            return stateNode;
        }

        Map<String, StateNode> stateNodeMap = stateNode.fetchAllChildNode();

        return stateNodeMap.get(currentStatus);
    }
}
