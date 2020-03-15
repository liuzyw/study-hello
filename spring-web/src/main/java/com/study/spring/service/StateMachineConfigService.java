package com.study.spring.service;

import com.study.spring.state.model.StateMachineConfigDefinition;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public interface StateMachineConfigService {

    StateMachineConfigDefinition getStateMachineConfigByBizCategory(String bizCategory);

}
