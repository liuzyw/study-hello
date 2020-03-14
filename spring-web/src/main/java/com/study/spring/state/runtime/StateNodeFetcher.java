package com.study.spring.state.runtime;

import com.study.spring.state.model.StateNode;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface StateNodeFetcher {

    StateNode startNode(String bizCategory);


    StateNode fetchStateNode(String bizCategory,String currentStatus);

}
