package com.study.rpc;

import java.util.List;

/**
 * Created on 2018-12-15
 *
 * @author liuzhaoyuan
 */
public interface LoadBalanceStrategy {

    String selectHost(List<String> serviceRepos);

}
