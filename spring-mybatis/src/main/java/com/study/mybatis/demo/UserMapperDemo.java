package com.study.mybatis.demo;

import com.study.mybatis.entity.Sex;
import com.study.mybatis.entity.User;
import com.study.mybatis.mapper.UserMapper;
import com.study.mybatis.util.MyUtils;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class UserMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            MyUtils.pringBegin();

            User user = new User();
            user.setName(MyUtils.getRandomString(6));
            user.setPass(MyUtils.getRandomString(6));
            user.setAge(MyUtils.getRandomInteger(60));
            user.setAddress(MyUtils.getRandomString(6));
            user.setSex(Sex.FEMALE);
            int c = userMapper.saveUser(user);
//      userMapper.deleteUser(9);

            System.out.println("----------  " + c);
      System.out.println("----------  " + userMapper.getUserById(20));
            System.out.println("----------  " + userMapper.getUserSimpleById(20));

            User user1 = new User();
//            user1.setId(19);
            user1.setName("SJCipi");
            System.out.println(userMapper.finUserByChoose(user1));

            System.out.println(userMapper.findUserByNameAndPass("liu","123"));


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
