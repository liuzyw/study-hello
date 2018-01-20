package com.study.sso.service.impl;

import com.study.sso.mapper.mapper2.UserDao;
import com.study.sso.po.User;
import com.study.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
