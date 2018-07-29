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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init ... ");
    }

    /**
     * 用于请求合并
     *
     * @param request
     * @param response
     * @param chain
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
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

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy ... ");
    }

}
