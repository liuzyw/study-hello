package com.study.util.sort.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 2018-10-31
 *
 * @author liuzhaoyuan
 */
public class GetMaxNumber {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("32");
        list.add("3");
        list.add("321");

        Collections.sort(list, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        System.out.println(list);

    }

}
