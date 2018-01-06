package com.study.spring.service.impl;

import com.study.spring.mapper.BookDao;
import com.study.spring.entity.Book;
import com.study.spring.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-10-13
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

    @Override
    public Integer deleteBook(Integer id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public List<Book> findBookByType(String type) {
        return bookDao.findBookByType(type);
    }
}
