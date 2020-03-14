package com.study.spring.state.runtime;

import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface StateMachine {


    StateMachineContext init(String bizCategory, Map<String, Object> input);


    StateMachineContext push(String bizOrderId, Map<String, Object> input);

}
