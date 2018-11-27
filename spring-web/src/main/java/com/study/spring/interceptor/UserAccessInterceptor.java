package com.study.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
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

//    @Autowired
//    private FruitService fruitService;

    /**
     * 在 controller 方法之前执行，若是返回 false 这整个请求结束
     *
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        System.out.println("http method: " + request.getMethod());

        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;

            System.out.println("request method: " + h.getMethod().getName());
        }

        // 获取用户信息
//        Fruit fruit = fruitService.getFruitById(2);
//        LOGGER.info("UserAccessInterceptor get fruit:{}", fruit);
        // 权限校验
        String url = request.getServletPath();
        if ("/goInter".equals(url)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/home.jsp");
            return false;
        }

        ValueThreadLocal.set("threadLocal");
        request.setAttribute("localValue", "Tom");
        return super.preHandle(request, response, handler);
    }

    /**
     * 在 controller 方法完成后执行 ，依赖前面的方法 返回 true， 可对 ModelAndView 进行操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        LOGGER.info("postHandle ... ");
        super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * 在整个请求完成后执行，主要用于清理资源， 依赖于前面的方法返回 true
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        ValueThreadLocal.remove();
        LOGGER.info("interceptor completion");
        super.afterCompletion(request, response, handler, ex);
    }
}
