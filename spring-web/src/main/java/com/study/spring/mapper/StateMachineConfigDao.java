package com.study.spring.mapper;

import com.study.spring.entity.StateMachineConfig;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
@Repository
public interface StateMachineConfigDao {


    StateMachineConfig getStateMachineConfigByBizCategory(String bizCategory);

}
