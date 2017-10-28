package com.study.util.security;

import java.security.MessageDigest;

/**
 * Created on 2017-10-26
 *
 * @author liuzhaoyuan
 */
public class SHAUtils {

    public static void main(String[] args) {
        sha256();
        sha512();
    }

    public static void sha256() {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update("liu".getBytes());
            System.out.println(HexUtil.encodeHexStr(messageDigest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sha512() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update("liu".getBytes());
            System.out.println(HexUtil.encodeHexStr(messageDigest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
