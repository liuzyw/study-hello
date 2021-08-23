package com.study.handler;

import com.study.handler.condition.ConditionHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class StateHandlerServiceImpl implements StateHandlerService {

    private Map<String, ConditionHandler> conditionHandlerMap;

    public StateHandlerServiceImpl() {
        conditionHandlerMap = new HashMap<>();
    }

    @Override
    public void registerConditionHandler(ConditionHandler conditionHandler) {
        conditionHandlerMap.put(conditionHandler.getConditionName(), conditionHandler);
    }

    @Override
    public ConditionHandler getConditionHandler(String name) {
        return conditionHandlerMap.get(name);
    }
}
