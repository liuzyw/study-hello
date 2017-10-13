package com.study.spring.boot.web.controller;

import com.study.spring.boot.web.po.Book;
import com.study.spring.boot.web.service.BookService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2017-10-12
 *
 * @author liuzhaoyuan
 */
@Controller
public class ThymeleafController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBookList")
    public String getBookList(Model model) {
        List<Book> books = bookService.findBookByType("小学");
        model.addAttribute("books", books);
        model.addAttribute("count", books.size());
        LOGGER.info("getBookList" + books);
        return "bookList";
    }


}
