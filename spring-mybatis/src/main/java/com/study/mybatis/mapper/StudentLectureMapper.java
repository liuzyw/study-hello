package com.study.mybatis.mapper;

import com.study.mybatis.entity.StudentLecture;
import java.util.List;

/**
 * Created on 2017-08-10 23:00
 *
 * @author liuzhaoyuan
 */
public interface StudentLectureMapper {

    List<StudentLecture> findStudentLectureByStudentId(Integer studentId);

}
