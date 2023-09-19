package com.study.timewheel.entity;


import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.time.Instant;

public class PemUtilTest {

    @Test
    public void testRsaCSR() {
        try {

            String rsaKeyPath = "src/test/rsa/doupay.req.pem";
            String rsaPrivateKeyPath = "src/test/rsa/doupay.key.pem";
            //参数需要替换成商户自己相关信息
            createCsrFile("DOUYINPAY@抖音支付-test", "beijing", "beijing", "test@douyinpay.com");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 创建私钥和csr文件
     *
     * @param subject  主体名称：商户申请标识
     * @param province 省份
     * @param city     市区
     * @param email    公司邮箱
     * @throws Exception
     */
    public void createCsrFile(String subject, String province, String city, String email) throws Exception {
        String rsaKeyPath = "src/test/rsa/doupay.req.pem";
        String rsaPrivateKeyPath = "src/test/rsa/doupay.key.pem";


        Files.createDirectories(Paths.get("src/test/rsa"));

        //1.创建密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();


        X500NameBuilder localX500NameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        /**
         * CN项用于标识订户申请者的标记，各项分类信息可用”@”符号进行分割，第一个“@”与第二个“@”之间要求为用户唯一标识信息（个人证书记录身份证号，企业证书记录企业统一信用代码）。如只有1个@符号，则@符后为唯一标识。以下为合规标识，供参考：
         * CN=1@110108199210195437@张三
         * CN=张三@110108199210195437
         */
        localX500NameBuilder.addRDN(BCStyle.CN, subject); //个人，企业用户名
        localX500NameBuilder.addRDN(BCStyle.C, "CN");  //国家：默认中国
        localX500NameBuilder.addRDN(BCStyle.O, "武汉合众易宝科技有限公司"); //默认不需要修改
        localX500NameBuilder.addRDN(BCStyle.ST, province);  //省
        localX500NameBuilder.addRDN(BCStyle.L, city);  //市
        localX500NameBuilder.addRDN(BCStyle.E, email); //邮箱

        org.bouncycastle.asn1.x500.X500Name localX500Name = localX500NameBuilder.build();

        // 使用私钥和 SHA256WithRSA/SM3withSM2 算法创建签名者对象
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA")
                .setProvider(new BouncyCastleProvider())
                .build(keyPair.getPrivate());

        // 创建 CSR
        PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(localX500Name, keyPair.getPublic());
        PKCS10CertificationRequest csr = builder.build(signer);

        //2.生成本地文件CSR
        try (FileWriter fileWriter = new FileWriter(rsaKeyPath)) {
            PemWriter pemWriter = new PemWriter(fileWriter);
            PemObject pem = new PemObject("CERTIFICATE REQUEST", csr.getEncoded());
            pemWriter.writeObject(pem);
            pemWriter.close();

        } catch (IOException e) {
            throw e;
        }

        //3.生成本地文件私钥
        try (FileWriter fileWriter = new FileWriter(rsaPrivateKeyPath)) {
            PemWriter pemWriter = new PemWriter(fileWriter);
            PemObject pem = new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded());
            pemWriter.writeObject(pem);
            pemWriter.close();

        } catch (IOException e) {
            throw e;
        }


    }


}
