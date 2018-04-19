package com.study.spring.cloud.dao;

import com.study.spring.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-04-18
 *
 * @author liuzhaoyuan
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}
