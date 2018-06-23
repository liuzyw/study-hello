package com.study.spring.service.impl;

import com.study.spring.entity.Book;
import com.study.spring.mapper.BookDao;
import com.study.spring.service.BookService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer testBookBy2(Book book) {
        bookDao.saveBook(book);
        System.out.println("success 2");
        int c = 3 / 0;
        return 1;
    }

    @Transactional
    @Override
    public Integer testBookBy1(Book book) {
        bookDao.saveBook(book);
        System.out.println("success 1");
        /**
         * 不加 try 两条记录均不成功
         *
         * 加 try 两天记录均成功
         *
         * 原因是由于 动态代理导致saveBookByTest1没有走aop  ，如何解决，让 saveBookByTest1 走aop
         */
        book.setName(book.getName() + "2");
        try {
            ((BookService) AopContext.currentProxy()).testBookBy2(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }


    @Override
    public Integer saveBookBy2(Book book) {
        bookDao.saveBook(book);
        System.out.println("success 22");
        int c = 3 / 0;
        return 4;
    }

    @Override
    public Integer saveBookBy1(Book book) {
        bookDao.saveBook(book);
        System.out.println("success 11");
        /**
         * 不加 try 两条记录均不成功
         *
         * 加 try 两天记录均成功
         *
         * 原因是由于 动态代理导致saveBookByTest1没有走aop  ，如何解决，让 saveBookByTest1 走aop
         */
        book.setName(book.getName() + "2");
        try {
            ((BookService) AopContext.currentProxy()).saveBookBy2(book);
//            saveBookByTest1(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }
}
