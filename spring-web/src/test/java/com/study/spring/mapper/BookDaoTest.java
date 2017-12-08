package com.study.spring.mapper;

import com.study.spring.po.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2017-11-30
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testSave() {
        Book book = new Book();
        book.setName("java优化");
        book.setPrice(45);
        book.setType("java");

        int re = bookDao.saveBook(book);
        System.out.println(book.getId());
    }

}
