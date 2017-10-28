package com.study.util.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 * Created on 2017-10-26
 *
 * @author liuzhaoyuan
 */
public class RSAUtils {

    public static void main(String[] args) {
        rsa();
    }

    public static void rsa() {

        // 1初始化密钥
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateCrtKey rsaPrivateCrtKey = (RSAPrivateCrtKey) keyPair.getPrivate();
            System.out.println("rsa public key: " + Base64.encode(rsaPublicKey.getEncoded()));
            System.out.println("rsa private key: " + Base64.encode(rsaPrivateCrtKey.getEncoded()));

            // 2私钥加密，公钥解密 -- 私钥加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateCrtKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal("liu".getBytes());
            System.out.println("private encode: " + Base64.encode(result));
            // 公钥解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("public decode: " + new String(result));

            // 3公钥加密,私钥解密  公钥加密
            x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal("liu".getBytes());
            System.out.println("public encode: " + Base64.encode(result));
            // 私钥解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("private decode: " + new String(result));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
