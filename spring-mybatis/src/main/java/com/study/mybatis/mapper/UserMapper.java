package com.study.mybatis.mapper;

import com.study.mybatis.entity.User;
import com.study.mybatis.entity.UserSimple;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public interface UserMapper {

    User getUserById(Integer id);

    User finUserByChoose(User user);
    // 多个参数需要这样设置
    User findUserByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    Integer saveUser(User user);

    Integer deleteUser(Integer id);

    UserSimple getUserSimpleById(Integer id);

}
