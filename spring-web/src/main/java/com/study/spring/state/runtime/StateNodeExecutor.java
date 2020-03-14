package com.study.spring.state.runtime;

import com.study.spring.state.action.ActionResult;
import com.study.spring.state.model.StateNode;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface StateNodeExecutor {


    ActionResult executeNode(StateNode stateNode, Map<String, Object> input);

}
