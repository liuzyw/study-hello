package com.study.mybatis.demo;

import com.study.mybatis.entity.StudentLecture;
import com.study.mybatis.mapper.StudentLectureMapper;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class StudentLectureMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentLectureMapper mapper = sqlSession.getMapper(StudentLectureMapper.class);

            List<StudentLecture> po = mapper.findStudentLectureByStudentId(10004);

            System.out.println(po);

            System.out.println("------------- done -----------");

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
