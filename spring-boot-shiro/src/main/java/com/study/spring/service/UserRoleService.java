package com.study.spring.service;

import com.study.spring.entity.SysRole;
import com.study.spring.entity.UserInfo;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
public interface UserRoleService {

    void fillUserInfo(UserInfo userInfo);

    void fillRole(SysRole sysRole);

}
