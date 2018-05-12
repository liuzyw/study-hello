package com.study.data.table;

import com.study.data.table.entity.User;
import com.study.data.table.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;


    @Test
    public void findUserById() {
        System.out.println(userService.findUserById(12));
        System.out.println("-------------");
    }

    @Test
    public void saverUser() {
        User user = new User();
        user.setName("yonggang");
        user.setPass("3333");
        user.setAge(28);
        user.setAddress("jiadingbei");

        userService.saveUser(user);

        System.out.println("------------> " + user.getId());

        System.out.println(userService.findUserById(user.getId()));



    }
}
