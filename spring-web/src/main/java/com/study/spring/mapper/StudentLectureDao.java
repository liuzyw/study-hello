package com.study.spring.mapper;

import com.study.spring.entity.StudentLecture;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017-08-10 23:00
 *
 * @author liuzhaoyuan
 */
@Repository
public interface StudentLectureDao {

    List<StudentLecture> findStudentLectureByStudentId(Integer studentId);

}
