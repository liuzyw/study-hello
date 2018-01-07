package com.study.spring.service.impl;

import com.study.spring.mapper.SysPermissionDao;
import com.study.spring.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

}
