package com.springmvc.service.impl;

import com.springmvc.annotation.MyService;
import com.springmvc.service.HelloService;
import com.springmvc.service.WordService;

/**
 * Created on 2018-04-21
 *
 * @author liuzhaoyuan
 */
@MyService
public class WordServiceImpl implements WordService {

    @Override
    public void sayHello() {
        System.out.println(" word service say hello");
    }
}
