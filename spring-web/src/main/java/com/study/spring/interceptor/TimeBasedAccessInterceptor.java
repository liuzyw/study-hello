package com.study.spring.interceptor;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created on 2018-07-22
 *
 * <p>一个 handleMapper 拦截器,只在规定的时间内才可以访问</>
 *
 * @author liuzhaoyuan
 */
public class TimeBasedAccessInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeBasedAccessInterceptor.class);

    private int openingTime;
    private int closingTime;

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (openingTime <= hour && hour < closingTime) {
            return true;
        }
        LOGGER.info("------------  TimeBasedAccessInterceptor  ----------");
        response.sendRedirect("http://localhost:8080/home.jsp");
        return false;
    }

}
