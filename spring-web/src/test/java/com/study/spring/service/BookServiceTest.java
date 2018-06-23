package com.study.spring.service;

import com.study.spring.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2018-05-23
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testTx1() {
        Book book = new Book();

        book.setName("java高并发设计2");
        book.setType("java");
        book.setPrice(48);

//        bookService.saveBook(book);
//        System.out.println(bookService.getBookById(book.getId()));

        bookService.testBookBy1(book);

//        bookService.saveBookBy1(book);
    }

}
