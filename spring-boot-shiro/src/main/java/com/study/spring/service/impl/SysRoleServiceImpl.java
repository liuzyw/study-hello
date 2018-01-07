package com.study.spring.service.impl;

import com.study.spring.mapper.SysRoleDao;
import com.study.spring.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleDao sysRoleDao;

}
