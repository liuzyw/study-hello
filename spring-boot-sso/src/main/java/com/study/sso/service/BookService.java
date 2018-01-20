package com.study.sso.service;

import com.study.sso.po.Book;
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
