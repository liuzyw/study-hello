package com.study.sso.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018-01-04
 *
 * @author liuzhaoyuan
 */
public class CookieUtils {

    private static final String DEFAULT_NAME = "sso-cookie";
    private static final String DEFAULT_VALUE = "sso";

    private CookieUtils() {
    }


    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 10);
        response.addCookie(cookie);
    }

    public static boolean checkCookie(HttpServletRequest request, String name, String value) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName()) && value.equals(cookie.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

}
