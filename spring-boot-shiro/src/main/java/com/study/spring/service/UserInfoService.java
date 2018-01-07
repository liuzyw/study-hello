package com.study.spring.service;

import com.study.spring.entity.UserInfo;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
public interface UserInfoService {

    UserInfo findUserInfoByAccount(String account);

    UserInfo findUserInfoById(Long id);

    Long deleteUserInfoByAccount(String account);

    Long saveUserInfo(UserInfo userInfo);

}
