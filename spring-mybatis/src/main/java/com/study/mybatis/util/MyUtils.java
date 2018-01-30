package com.study.mybatis.util;

import java.util.Random;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public class MyUtils {

  private MyUtils() {
  }

  /**
   * 随机生成指定长度的字符串
   *
   * @return String
   */
  public static String getRandomString(int length) {
    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXYZ";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    //length表示生成字符串的长度
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }

  /**
   *
   * @param max
   * @return
   */
  public static int getRandomInteger(int max) {
    Random random = new Random();
    return random.nextInt(max);

  }

  public static void pringBegin(){
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("-------------- begin -------------");
    System.out.println();
    System.out.println();

  }

  public static void pringEnd(){
    System.out.println();
    System.out.println();
    System.out.println("-------------- done -------------");
    System.out.println();
    System.out.println();
    System.out.println();

  }
}
