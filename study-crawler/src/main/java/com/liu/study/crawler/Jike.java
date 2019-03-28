package com.liu.study.crawler;

import lombok.Data;

/**
 * Created on 2019-03-26
 *
 * @author liuzhaoyuan
 */
@Data
public class Jike {

    private static final Long serialVersionUID = 1L;

    private int appid;
    private int country;
    private String cellphone;
    private String password;
    private String captcha;
    private int remember;
    private int platform;


    public static Jike get() {
        Jike jike = new Jike();
        jike.setAppid(1);
        jike.setCountry(86);
        jike.setCellphone("18612256271");
        jike.setPassword("mit12345");
        jike.setCaptcha("");
        jike.setRemember(1);
        jike.setPlatform(3);

        return jike;
    }

}
