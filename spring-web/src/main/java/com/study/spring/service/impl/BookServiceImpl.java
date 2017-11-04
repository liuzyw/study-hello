package com.study.spring.service.impl;

import com.study.spring.mapper.BookDao;
import com.study.spring.po.Book;
import com.study.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-08-17 21:46
 *
 * @author liuzhaoyuan
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }
    @Override
    public Integer saveBook(Book book) {
        return bookDao.saveBook(book);
    }

}
