package com.study.spring.mapper;

import com.study.spring.po.StudentLecture;
import java.util.List;

/**
 * Created on 2017-08-10 23:00
 *
 * @author liuzhaoyuan
 */
public interface StudentLectureDao {

    List<StudentLecture> findStudentLectureByStudentId(Integer studentId);

}
