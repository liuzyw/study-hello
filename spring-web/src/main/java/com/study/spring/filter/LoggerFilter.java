package com.study.spring.filter;

import com.study.spring.entity.Fruit;
import com.study.spring.service.FruitService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created on 2018-01-17
 *
 * @author liuzhaoyuan
 */
@Component
public class LoggerFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFilter.class);

    @Autowired
    private FruitService fruitService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        Fruit fruit = fruitService.getFruitById(2);
        LOGGER.info("LoggerFilter get fruit:{}", fruit);

        String url = request.getServletPath();
        if ("/goFilter".equals(url)) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("... welcome home ...");

//            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
