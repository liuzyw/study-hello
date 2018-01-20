package com.study.sso.service;

import com.study.sso.po.User;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
public interface UserService {

    User findByUsername(String username);


}
