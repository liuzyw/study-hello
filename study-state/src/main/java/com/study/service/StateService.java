package com.study.service;

import com.study.entity.StateRequest;
import com.study.entity.StateResult;

public interface StateService {

    StateResult initState(StateRequest request);

    StateResult pushState(StateRequest request);
}
