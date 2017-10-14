package com.study.spring.boot.web.service;

import com.study.spring.boot.web.Application;
import javax.sound.midi.Soundbank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2017-10-14
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBookById(){
        System.out.println(bookService.getBookById(4));
    }
}
