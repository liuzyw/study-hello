package com.study.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2019-03-27
 *
 * @author liuzhaoyuan
 */
public class Demo1 {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {

        String src = "asfasd12345678dasdsadsa-=*&^$#$!,.;'dasda087482vkdfd3";


        Pattern p = Pattern.compile("\\d{3}");

        Matcher m = p.matcher(src);


        while (m.find()){
            System.out.println(m.group());
        }


    }

}
