package com.study.spring.boot.web.service;

import com.study.spring.boot.web.SpringBootWebApplication;
import com.study.spring.boot.web.mapper.BookDao;
import com.study.spring.boot.web.po.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringBootWebApplication.class)
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;


    @Test
    @Transactional
    public void testTransaction(){

        bookDao.deleteBook(16);
        int a = 3/0;
        Book book = new Book();
        book.setName("mysql");
        book.setPrice(45);
        book.setType("sql");
//        bookDao.saveBook(book);
    }

}
