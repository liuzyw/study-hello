package com.study.spring.boot.web.mapper;

import com.study.spring.boot.web.po.Book;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
@Repository
public interface BookDao {

    Book getBookById(Integer id);

    Integer saveBook(Book book);

    Integer deleteBook(Integer id);

    List<Book> findBookByType(String type);

}
