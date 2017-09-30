package com.study.spring.mapper;

import com.study.spring.po.Student;

/**
 * Created on 2017-08-10 21:51
 *
 * @author liuzhaoyuan
 */
public interface StudentDao {

    Student getStudentById(Integer id);
}
