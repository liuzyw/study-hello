package com.study.spring.mapper;

import com.study.spring.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
@Repository
public interface UserDao {

    User getUserById(Integer id);

    User finUserByChoose(User user);
    // 多个参数需要这样设置
    User findUserByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    Integer saveUser(User user);

    Integer deleteUser(Integer id);

}
