package com.study.spring.boot.web.controller;

import com.study.spring.boot.web.po.Book;
import com.study.spring.boot.web.service.BookService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017-10-12
 *
 * @author liuzhaoyuan
 */
@RestController
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;


    @RequestMapping("/getBookById")
    public Book getBookById() {
        Book book = bookService.getBookById(11);
        LOGGER.info("getBookById" + book);
        return book;
    }

    @RequestMapping("/getBook/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        LOGGER.info("getBookById,id=" + id);
        return bookService.getBookById(id);
    }

    @RequestMapping("/getBookList1")
    public List<Book> getBookList() {
        LOGGER.info("getBookList");
        return bookService.findBookByType("小学");
    }

}
