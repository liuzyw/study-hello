package com.study.sso.mapper.mapper2;

import com.study.sso.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@Repository
public interface UserDao {

    User findByUsername(String username);

}
