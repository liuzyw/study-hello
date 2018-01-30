package com.study.mybatis.mapper;

import com.study.mybatis.entity.Student;

/**
 * Created on 2017-08-10 21:51
 *
 * @author liuzhaoyuan
 */
public interface StudentMapper {

    Student getStudentById(Integer id);
}
