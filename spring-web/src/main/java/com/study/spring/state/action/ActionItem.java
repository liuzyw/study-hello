package com.study.spring.state.action;

import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface ActionItem<T> {


    /**
     * @param input
     * @return
     */

    T doBiz(Map<String, Object> input);

}
