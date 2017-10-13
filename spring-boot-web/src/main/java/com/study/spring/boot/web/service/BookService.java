package com.study.spring.boot.web.service;

import com.study.spring.boot.web.po.Book;
import java.util.List;

/**
 * Created on 2017-10-13
 *
 * @author liuzhaoyuan
 */
public interface BookService {

    Book getBookById(Integer id);

    Integer saveBook(Book book);

    Integer deleteBook(Integer id);

    List<Book> findBookByType(String type);
}
