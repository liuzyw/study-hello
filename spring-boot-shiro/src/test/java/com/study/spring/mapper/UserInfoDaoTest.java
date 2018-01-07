package com.study.spring.mapper;

import com.google.common.collect.Lists;
import com.study.spring.BaseTest;
import com.study.spring.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
public class UserInfoDaoTest extends BaseTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void testFindUserInfoById() {
        System.out.println("--------------");
        System.out.println(userInfoDao.findUserInfoById(1L));
        System.out.println(userInfoDao.findUserInfosByIds(Lists.newArrayList(1L,2L)));
    }

    @Test
    public void testSaveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("zhu");
        userInfo.setPassword("123");
        userInfo.setUsername("zhu");
        userInfo.setSalt("123");
        userInfo.setState(1);

        userInfoDao.saveUserInfo(userInfo);

        System.out.println("-------------- " + userInfo.getId());
    }

}
