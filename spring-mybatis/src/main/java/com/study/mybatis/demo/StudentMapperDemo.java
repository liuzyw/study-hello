package com.study.mybatis.demo;

import com.study.mybatis.entity.Student;
import com.study.mybatis.mapper.StudentMapper;
import com.study.mybatis.util.MyUtils;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class StudentMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            MyUtils.pringBegin();
            Student student = mapper.getStudentById(10002);
            System.out.println("----------1---------" + student);

            student.getStudentSelfCard();
            System.out.println("----------2---------"+student);
            Student student1 = mapper.getStudentById(10002);

            System.out.println(student);

            MyUtils.pringEnd();
            sqlSession.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
