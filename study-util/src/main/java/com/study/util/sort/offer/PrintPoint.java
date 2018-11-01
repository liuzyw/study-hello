package com.study.util.sort.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-11-01
 *
 * @author liuzhaoyuan
 */
public class PrintPoint {

    public static void main(String[] args) {
        find(3);


    }

    private static void find(int num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);

        for (int i = 1; i < num; i++) {

            Map<Integer, Integer> map2 = new HashMap<>();

            int min = i + 1;

            for (int j = 1; j < 7; j++) {
                inner:
                for (Integer key : map.keySet()) {
                    int next = key + j;
                    int atach = 0;

                    if (map.containsKey(next)) {
                        atach = map.get(next);
                   }
                    if (map2.containsKey(next)) {
                        map2.put(next, map2.get(next) + atach + 1);
                    } else {
                        map2.put(next, atach + 1);
                    }
                }
            }
            map = map2;
        }

        int sum = (int) Math.pow(6, num);

        for (Integer integer : map.keySet()) {
            System.out.println(integer + "   " + map.get(integer) + "/" + sum);
        }
    }

}
