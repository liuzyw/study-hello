package com.study.mybatis.demo;

import com.study.mybatis.entity.StudentSelfCard;
import com.study.mybatis.mapper.StudentSelfCardMapper;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class StudentSelfCardMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            StudentSelfCardMapper mapper = sqlSession.getMapper(StudentSelfCardMapper.class);

            StudentSelfCard student = mapper.findStudentSelfCardByStudentId(10002);

            System.out.println(student);

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
