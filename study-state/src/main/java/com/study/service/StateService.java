package com.study.service;

import com.study.entity.StateResult;

import java.util.Map;

public interface StateService {

    StateResult initState(Map<String, String> params);

    StateResult pushState(Map<String, String> params);
}
