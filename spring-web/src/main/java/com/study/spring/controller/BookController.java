package com.study.spring.controller;

import com.study.spring.entity.Book;
import com.study.spring.service.BookService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/findBookById", method = RequestMethod.POST)
    public String findBookById(Model model) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Book book = bookService.getBookById(id);
        LOGGER.info("model find book : " + book);
        model.addAttribute("book", book);
        return "book/showBook";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute Book book, Model model) {
        Integer re = bookService.saveBook(book);
        System.out.println("save book : " + re);
//        LOGGER.info("model add book : " + book);
//        model.addAttribute("book", book);
        return "redirect:/showBook/" + book.getId();
    }

    @RequestMapping(value = "/showBook/{bookId}", method = RequestMethod.GET)
    public String showBookByPath(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        LOGGER.info("model add book : " + book);
        model.addAttribute("book", book);
        return "book/showBook";
    }

    @RequestMapping(value = "/getMvBook", method = RequestMethod.POST)
    public ModelAndView getMv(@RequestHeader(value = "Accept") String[] accepts, ModelAndView modelAndView,
        @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId,
        @RequestHeader(value = "User-Agent") String userAgent) {
        LOGGER.info("accepts:{}", accepts);
        LOGGER.info("sessionId:{}", sessionId);
        LOGGER.info("userAgent:{}", userAgent);
        Integer id = Integer.valueOf(request.getParameter("id"));
        Book book = bookService.getBookById(id);
        modelAndView.addObject("book", book);
        modelAndView.setViewName("book/showBook");
//        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/book/puttRestful", method = RequestMethod.PUT)
    public Book putRestful(@RequestBody Book book) {
        System.out.println("puttRestful" + book);
        LOGGER.info("put restful : " + book);
        return book;
    }

    @ResponseBody
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public String deleteRestful(@RequestParam("id") Integer id) {

        LOGGER.info("delete restful : " + id);

        return "delete success";
    }


}
