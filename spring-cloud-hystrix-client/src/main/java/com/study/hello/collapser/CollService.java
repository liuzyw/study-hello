package com.study.hello.collapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.stereotype.Service;

@Service
public class CollService {

    @HystrixCollapser(batchMethod = "getMembers", collapserProperties = {
        @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
    })
    public Future<String> getMember(Integer id) {
        System.out.println("执行单个查询的方法");
        return null;
    }

    @HystrixCommand
    public List<String> getMembers(List<Integer> ids) {
        List<String> mems = new ArrayList<String>();
        for (Integer id : ids) {
            System.out.println(id);

            mems.add(String.valueOf(id));
        }
        return mems;
    }
}
