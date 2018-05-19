package com.study.hystrix.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class MyCommand extends HystrixCommand<String> {

    private int index;

    public MyCommand(int index) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
            .asKey("TestGroupKey")).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(5)
            .withMaxQueueSize(5)));
        this.index = index;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(50);
        System.out.println("执行方法，当前索引：" + index);
        return "";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行回退，当前索引：" + index);
        return "";
    }


}
