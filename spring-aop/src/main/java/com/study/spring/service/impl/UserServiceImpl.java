package com.study.spring.service.impl;

import com.study.spring.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-06-19
 *
 * @author liuzhaoyuan
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public void say() {
        System.out.println("user service ...");
    }
}
