package com.study.spring.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
@WebFilter(value = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("myfilter init ....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myfilter doFilter ....");


        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("myfilter destroy ....");

    }
}
