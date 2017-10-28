package com.study.util.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created on 2017-10-21
 *
 * @author liuzhaoyuan
 */
public class DESUtils {


    /**
     * 加密解密的对象要是同一个
     * @param src
     */
    public static void jdkEncode(String src){
        try {
            // 生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey= keyGenerator.generateKey();
            byte[] bytes = secretKey.getEncoded();

            // 得到密钥
            DESKeySpec desKeySpec = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key key = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println(HexUtil.encodeHex(result));

            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] result1 = cipher.doFinal(result);
            System.out.println(new String(result1));

        } catch (Exception e) {
        }
    }

    public static void jdkDecode(String src){
        try {
            // 生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey= keyGenerator.generateKey();
            byte[] bytes = secretKey.getEncoded();

            // 得到密钥
            DESKeySpec desKeySpec = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key key = factory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] result = cipher.doFinal(HexUtil.decodeHex(src));
            System.out.println(new String(result));
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        jdkEncode("dasdasdas");
        jdkDecode("b969e72bd9bb728f9c3a9a20c303fc08");
    }
}
