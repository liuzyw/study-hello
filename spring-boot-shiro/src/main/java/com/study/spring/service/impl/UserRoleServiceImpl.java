package com.study.spring.service.impl;

import com.study.spring.entity.SysPermission;
import com.study.spring.entity.SysRole;
import com.study.spring.entity.UserInfo;
import com.study.spring.mapper.RolePermissionDao;
import com.study.spring.mapper.SysPermissionDao;
import com.study.spring.mapper.SysRoleDao;
import com.study.spring.mapper.UserInfoDao;
import com.study.spring.mapper.UserRoleDao;
import com.study.spring.service.UserRoleService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public void fillUserInfo(UserInfo userInfo) {
        List<Integer> rids = userRoleDao.findRoleIdsByUserInfoId(userInfo.getId());
        List<SysRole> roles = sysRoleDao.findRolesByRoleIds(rids);
        if (CollectionUtils.isNotEmpty(roles)) {
            for (SysRole role : roles) {
                List<Integer> pids = rolePermissionDao.findPermissionIdsByRoleId(role.getId());
                List<SysPermission> permissions = sysPermissionDao.findPermissionsByIds(pids);
                role.setPermissions(permissions);
            }
        }

        userInfo.setRoleList(roles);
    }

    @Override
    public void fillRole(SysRole role) {
        List<Long> uids = userRoleDao.findUserInfoIdsByRoleId(role.getId());
        List<UserInfo> userInfos = userInfoDao.findUserInfosByIds(uids);
        role.setUserInfos(userInfos);

        List<Integer> pids = rolePermissionDao.findPermissionIdsByRoleId(role.getId());
        List<SysPermission> permissions = sysPermissionDao.findPermissionsByIds(pids);
        role.setPermissions(permissions);
    }
}
