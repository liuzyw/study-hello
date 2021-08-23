package com.study.util;

import com.study.model.StateConfig;
import com.study.model.StateNode;

import java.util.Collections;

public class StateConfigUtil {
    private StateConfigUtil() {
    }


    public static StateNode getCurrentNode(StateConfig stateConfig, String currentState) {
        Collections.sort(stateConfig.getStateNodes());

        for (StateNode stateNode : stateConfig.getStateNodes()) {
            if (stateNode.getStateName().equals(currentState)) {
                return stateNode;
            }
        }

        return null;
    }


}
