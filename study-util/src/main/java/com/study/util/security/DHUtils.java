package com.study.util.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/**
 * Created on 2017-10-24
 *
 * @author liuzhaoyuan
 */
public class DHUtils {


    public static void main(String[] args) {
        jdkDH();
    }

    public static void jdkDH() {

        try {
            // 初始化密钥发送方
            KeyPairGenerator sendKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            sendKeyPairGenerator.initialize(512);
            KeyPair sendKeyPair = sendKeyPairGenerator.generateKeyPair();
            // 发送公钥给接收方
            byte[] sendPublicKeyEnc = sendKeyPair.getPublic().getEncoded();

            // 初始化接收方密钥
            KeyFactory recekeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(sendPublicKeyEnc);
            PublicKey receivePublicKey = recekeyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receivePublicKey).getParams();
            KeyPairGenerator receiverGenerator = KeyPairGenerator.getInstance("DH");
            receiverGenerator.initialize(dhParameterSpec);
            KeyPair receiveKeyPair = receiverGenerator.generateKeyPair();
            PrivateKey receivePrivateKey = receiveKeyPair.getPrivate();
            byte[] receivePublicKeyEnc = receiveKeyPair.getPublic().getEncoded();

            // 密钥构建
            KeyAgreement recekeyAgreement = KeyAgreement.getInstance("DH");
            recekeyAgreement.init(receivePrivateKey);
            recekeyAgreement.doPhase(receivePublicKey, true);
            SecretKey receiveSecretKey = recekeyAgreement.generateSecret("DES");

            KeyFactory sendKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receivePublicKeyEnc);
            PublicKey sendPublicKey = sendKeyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement sendKeyAgreement = KeyAgreement.getInstance("DH");
            sendKeyAgreement.init(sendKeyPair.getPrivate());
            sendKeyAgreement.doPhase(sendPublicKey, true);

            SecretKey sendSecretKey = sendKeyAgreement.generateSecret("DES");
            if (Objects.equals(receiveSecretKey, sendSecretKey)) {
                System.out.println("--- key done ----");
            }

            // 加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, sendSecretKey);
            byte[] result = cipher.doFinal("liu".getBytes());
            System.out.println("jdk dh: " + Base64.encode(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, receiveSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk dh: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
