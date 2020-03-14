package com.study.spring.service;

import com.study.spring.entity.Book;
import com.study.spring.logger.DigestLogAnnotation;
import com.study.spring.logger.LoggerType;

/**
 * Created on 2017-08-17 21:45
 *
 * @author liuzhaoyuan
 */
public interface BookService {

    @DigestLogAnnotation(bizName = "保存书籍", loggerType = LoggerType.SERVICE, printParam = true, printResult = false)
    Book getBookById(Integer id);

    @DigestLogAnnotation(bizName = "保存书籍", loggerType = LoggerType.SERVICE, printParam = true, printResult = true)
    Integer saveBook(Book book);

    Integer testBookBy1(Book book);

    Integer testBookBy2(Book book);


    Integer saveBookBy1(Book book);

    Integer saveBookBy2(Book book);

}
