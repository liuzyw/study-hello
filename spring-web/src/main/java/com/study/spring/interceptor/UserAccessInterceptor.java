package com.study.spring.interceptor;

import com.study.spring.entity.Fruit;
import com.study.spring.service.FruitService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created on 2018-01-17
 * <p>
 * <p>自定义拦截器</p>
 *
 * @author liuzhaoyuan
 */
public class UserAccessInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccessInterceptor.class);

    @Autowired
    private FruitService fruitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // 获取用户信息
        Fruit fruit = fruitService.getFruitById(2);
        LOGGER.info("UserAccessInterceptor get fruit:{}", fruit);
        // 权限校验
        String url = request.getServletPath();
        if ("/goInter".equals(url)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/home.jsp");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
