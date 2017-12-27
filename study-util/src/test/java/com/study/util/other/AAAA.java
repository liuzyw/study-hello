package com.study.util.other;

/**
 * Created on 2017-12-27
 *
 * @author liuzhaoyuan
 */
public class AAAA {

    public static void main(String[] args) throws Exception {
        try {
            aaa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void aaa() throws Exception{
        for (int i =0;i<10;i++){
            System.out.println(i);
//            try {
                throw new RuntimeException("ddd");
//            } catch (RuntimeException e){
//                e.printStackTrace();
//            }
        }
    }

}
