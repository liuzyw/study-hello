package com.study.spring.mapper;

import com.study.spring.entity.StudentSelfCard;
import org.springframework.stereotype.Repository;

/**
 * Created on 2017-08-10 22:09
 *
 * @author liuzhaoyuan
 */
@Repository
public interface StudentSelfCardDao {

    StudentSelfCard findStudentSelfCardByStudentId(Integer id);

}
