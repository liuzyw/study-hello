package com.study.spring.state.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionItemRegistryImpl implements ActionItemRegistry {


    private Map<String, ActionItem> actionItemMap = new HashMap<>();


    public Map<String, ActionItem> getActionItemMap() {
        return actionItemMap;
    }

    public void setActionItemMap(Map<String, ActionItem> actionItemMap) {
        this.actionItemMap = actionItemMap;
    }

    @Override
    public ActionItem getActionItemByCode(String code) {
        ActionItem actionItem = actionItemMap.get(code);

        return actionItem;
    }
}
