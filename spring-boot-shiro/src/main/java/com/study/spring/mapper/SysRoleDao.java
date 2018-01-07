package com.study.spring.mapper;

import com.study.spring.entity.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Repository
public interface SysRoleDao {

    List<SysRole> findRolesByRoleIds(@Param("ids") List<Integer> ids);
}
