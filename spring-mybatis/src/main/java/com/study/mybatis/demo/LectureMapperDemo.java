package com.study.mybatis.demo;

import com.study.mybatis.entity.Lecture;
import com.study.mybatis.mapper.LectureMapper;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class LectureMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            LectureMapper mapper = sqlSession.getMapper(LectureMapper.class);

            Lecture po = mapper.getLectureById(2003);

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
