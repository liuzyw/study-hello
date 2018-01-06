package com.study.spring.service;

import com.study.spring.entity.Book;

/**
 * Created on 2017-08-17 21:45
 *
 * @author liuzhaoyuan
 */
public interface BookService {

    Book getBookById(Integer id);

    Integer saveBook(Book book);
}
