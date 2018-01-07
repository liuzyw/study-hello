package com.study.spring.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Repository
public interface UserRoleDao {

    List<Long> findUserInfoIdsByRoleId(Integer roleId);

    List<Integer> findRoleIdsByUserInfoId(Long userInfoId);
}
