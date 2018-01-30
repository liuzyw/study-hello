package com.study.spring.mapper;

import com.study.spring.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017-08-10 21:51
 *
 * @author liuzhaoyuan
 */
@Repository
public interface StudentDao {

    Student getStudentById(Integer id);
}
