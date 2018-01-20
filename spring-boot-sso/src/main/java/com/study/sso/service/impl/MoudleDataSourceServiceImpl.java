package com.study.sso.service.impl;

import com.study.sso.mapper.mapper1.BookDao;
import com.study.sso.mapper.mapper2.UserDao;
import com.study.sso.service.MoudleDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@Service
public class MoudleDataSourceServiceImpl implements MoudleDataSourceService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void findDate() {
        System.out.println(bookDao.getBookById(2));
        System.out.println(userDao.findByUsername("zhang"));
    }
}
