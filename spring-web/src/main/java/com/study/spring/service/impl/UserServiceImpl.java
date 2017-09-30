package com.study.spring.service.impl;

import com.study.spring.mapper.UserDao;
import com.study.spring.po.User;
import com.study.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-08-17 23:38
 *
 * @author liuzhaoyuan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }


}
