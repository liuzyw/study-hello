package com.study.spring.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class MyFilter extends ZuulFilter {

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        System.out.println("执行 MyFilter 过滤器");

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        System.out.println("url: " + request.getRequestURL() + ", uri: " + request.getRequestURI());
        System.out.println("method: " + request.getMethod());

        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
