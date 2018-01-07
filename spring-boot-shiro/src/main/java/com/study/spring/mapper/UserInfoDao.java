package com.study.spring.mapper;

import com.study.spring.entity.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Repository
public interface UserInfoDao {

    UserInfo findUserInfoByAccount(String account);

    UserInfo findUserInfoById(Long id);

    List<UserInfo> findUserInfosByIds(@Param("ids") List<Long> ids);

    Long deleteUserInfoByAccount(String account);

    Long saveUserInfo(UserInfo userInfo);


}
