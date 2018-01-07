package com.study.spring.service;

import com.study.spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
public class UserInfoServiceTest extends BaseTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testUserInfo(){
        System.out.println(userInfoService.findUserInfoById(1L));
    }

}
