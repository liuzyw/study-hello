package com.study.rpc;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created on 2018-12-15
 *
 * @author liuzhaoyuan
 */
public abstract class AbstractLoadBalanceStrategy implements LoadBalanceStrategy {

    @Override
    public String selectHost(List<String> serviceRepos) {

        if (CollectionUtils.isEmpty(serviceRepos)) {
            return null;
        }
        if (serviceRepos.size() == 1) {
            return serviceRepos.get(0);
        }

        return doSelect(serviceRepos);
    }

    protected abstract String doSelect(List<String> serviceRepos);
}
