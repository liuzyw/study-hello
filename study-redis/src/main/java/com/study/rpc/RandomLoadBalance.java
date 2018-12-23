package com.study.rpc;

import java.util.List;
import java.util.Random;

/**
 * Created on 2018-12-15
 *
 * @author liuzhaoyuan
 */
public class RandomLoadBalance extends AbstractLoadBalanceStrategy {

    @Override
    protected String doSelect(List<String> serviceRepos) {
        Random random = new Random();
        return serviceRepos.get(random.nextInt(serviceRepos.size()));
    }
}
