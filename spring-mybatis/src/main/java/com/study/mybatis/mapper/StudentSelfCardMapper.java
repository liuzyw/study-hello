package com.study.mybatis.mapper;

import com.study.mybatis.entity.StudentSelfCard;

/**
 * Created on 2017-08-10 22:09
 *
 * @author liuzhaoyuan
 */
public interface StudentSelfCardMapper {

    StudentSelfCard findStudentSelfCardByStudentId(Integer id);

}
