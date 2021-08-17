package com.study.service;

import java.util.Map;

public interface RuleService {

    boolean executeRule(String ruleId, Map<String, String> params);

}
