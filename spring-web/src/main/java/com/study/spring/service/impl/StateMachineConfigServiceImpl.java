package com.study.spring.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.spring.entity.StateMachineConfig;
import com.study.spring.mapper.StateMachineConfigDao;
import com.study.spring.service.StateMachineConfigService;
import com.study.spring.state.model.StateMachineConfigDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
@Service
public class StateMachineConfigServiceImpl implements StateMachineConfigService {

    @Autowired
    private StateMachineConfigDao stateMachineConfigDao;

    @Override
    public StateMachineConfigDefinition getStateMachineConfigByBizCategory(String bizCategory) {

        StateMachineConfig machineConfig = stateMachineConfigDao.getStateMachineConfigByBizCategory(bizCategory);
        return convertToDefinition(machineConfig);
    }


    private StateMachineConfigDefinition convertToDefinition(StateMachineConfig stateMachineConfig) {
        if (stateMachineConfig == null) {
            return null;
        }
        StateMachineConfigDefinition definition = new StateMachineConfigDefinition();

        definition.setBizCategory(stateMachineConfig.getBizCategory());

        StateMachineConfigDefinition content = JSONObject.parseObject(stateMachineConfig.getContent(), StateMachineConfigDefinition.class);

        definition.setStateNodeDefinition(content == null ? null : content.getStateNodeDefinition());

        return definition;
    }
}
