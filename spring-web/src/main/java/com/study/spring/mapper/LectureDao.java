package com.study.spring.mapper;

import com.study.spring.entity.Lecture;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017-08-10 22:51
 *
 * @author liuzhaoyuan
 */
@Repository
public interface LectureDao {

    Lecture getLectureById(Integer id);
}
