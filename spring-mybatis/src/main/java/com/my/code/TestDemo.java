package com.my.code;

import com.my.code.config.MySqlsession;
import com.my.code.mapper.MyUserMapper;

/**
 * Created on 2018-03-23
 *
 * @author liuzhaoyuan
 */
public class TestDemo {

    public static void main(String[] args) {

        MySqlsession sqlsession = new MySqlsession();
        MyUserMapper mapper = sqlsession.getMapper(MyUserMapper.class);
        MyUser myUser = mapper.getUserById(12);
        System.out.println("------");
        System.out.println(myUser);
    }
}
