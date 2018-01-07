package com.study.spring.service.impl;

import com.study.spring.entity.UserInfo;
import com.study.spring.mapper.UserInfoDao;
import com.study.spring.service.UserInfoService;
import com.study.spring.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo findUserInfoByAccount(String account) {
        UserInfo userInfo = userInfoDao.findUserInfoByAccount(account);
        userRoleService.fillUserInfo(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo findUserInfoById(Long id) {
        UserInfo userInfo = userInfoDao.findUserInfoById(id);
        userRoleService.fillUserInfo(userInfo);
        return userInfo;
    }

    @Override
    public Long deleteUserInfoByAccount(String account) {
        return userInfoDao.deleteUserInfoByAccount(account);
    }

    @Override
    public Long saveUserInfo(UserInfo userInfo) {
        userInfoDao.saveUserInfo(userInfo);
        return userInfo.getId();
    }
}
