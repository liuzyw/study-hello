package com.study.spring.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Repository
public interface RolePermissionDao {

    List<Integer>  findRoleIdsByPermissionId(Integer pid);

    List<Integer>  findPermissionIdsByRoleId(Integer rid);

}
