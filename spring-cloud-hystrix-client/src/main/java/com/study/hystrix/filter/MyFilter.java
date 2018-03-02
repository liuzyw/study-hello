package com.study.hystrix.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init ... ");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter doFilter ... ");

        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {

        } finally {
            ctx.shutdown();
        }
    }

    public void destroy() {
        System.out.println("MyFilter destroy ... ");
    }

}
