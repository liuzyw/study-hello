package com.study.sso.util;

/**
 * Created on 2018-01-04
 *
 * @author liuzhaoyuan
 */
public class SSOUtils {

    private static final String USERNAME = "liu";
    private static final String PASSWORD = "123";

    private SSOUtils() {
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (USERNAME.equalsIgnoreCase(username) && PASSWORD.equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }

}
