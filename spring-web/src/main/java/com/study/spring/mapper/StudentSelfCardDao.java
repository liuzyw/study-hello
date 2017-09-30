package com.study.spring.mapper;

import com.study.spring.po.StudentSelfCard;

/**
 * Created on 2017-08-10 22:09
 *
 * @author liuzhaoyuan
 */
public interface StudentSelfCardDao {

    StudentSelfCard findStudentSelfCardByStudentId(Integer id);

}
