package com.study.data.table.mapper.user;

import com.study.data.table.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
@Repository
public interface UserMapper {

    void saveUser(User user);

    User findUserById(Integer id);

    int deleteUserById(Integer id);

}
