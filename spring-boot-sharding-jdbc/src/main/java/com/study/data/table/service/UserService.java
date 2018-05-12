package com.study.data.table.service;

import com.study.data.table.entity.User;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
public interface UserService {

    void saveUser(User user);

    User findUserById(Integer id);

    int deleteUserById(Integer id);

}
