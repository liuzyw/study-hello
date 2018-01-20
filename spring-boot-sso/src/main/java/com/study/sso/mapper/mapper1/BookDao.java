package com.study.sso.mapper.mapper1;

import com.study.sso.po.Book;
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
