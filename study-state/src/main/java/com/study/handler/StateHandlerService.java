package com.study.handler;

import com.study.handler.condition.ConditionHandler;

public interface StateHandlerService {


    void registerConditionHandler(ConditionHandler conditionHandler);


    ConditionHandler getConditionHandler(String name);

}
