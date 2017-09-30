package com.study.spring.mapper;

import com.study.spring.po.Lecture;

/**
 * Created on 2017-08-10 22:51
 *
 * @author liuzhaoyuan
 */
public interface LectureDao {

    Lecture getLectureById(Integer id);
}
