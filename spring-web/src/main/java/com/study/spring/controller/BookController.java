package com.study.spring.controller;

import com.study.spring.po.Book;
import com.study.spring.po.Fruit;
import com.study.spring.service.BookService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 2017-08-17 21:48
 *
 * @author liuzhaoyuan
 */
@Controller
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);


    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/findBookById", method = RequestMethod.POST)
    public String findBookById(HttpServletRequest request, Model model) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Book book = bookService.getBookById(id);
        LOGGER.info("model find book : " + book);
        model.addAttribute("book", book);
        return "book/showBook";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(Book book, Model model) {
        Integer re = bookService.saveBook(book);
        System.out.println("save book : " + re);
//        LOGGER.info("model add book : " + book);
//        model.addAttribute("book", book);
        return "redirect:/showBook/11";
    }

    @RequestMapping(value = "/showBook/{bookId}",method = RequestMethod.GET)
    public String showBookByPath(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        LOGGER.info("model add book : " + book);
        model.addAttribute("book", book);
        return "book/showBook";
    }
}
