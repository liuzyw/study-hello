package com.study.spring.pay.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091800540194";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCf1cYeBLSgWGf4Ct5HuPJw8yp"
        + "nf5wb6CyllRe+TeqrfrhlauPWZEv2UUxM7J1YfUTLJi9n3+5HFYGqS42o91ar91jiT8o0Pg9UbsUk2vyBMxTUOEhkWN2Yl2fQ5N52mPFn9Nn6QerUVB"
        + "DC4OibFwcFoF1NE5VC2npGzYkwG0Awnpqj3ydF0Cko5zecw8PqBqOL33jQhj0Jp1yvM5fVgdTl1SDBs9TWN/pABTwe6AaOW9xyfYS1MnL/WPVlA0HSX"
        + "bsOLlaxT4Lwt64qGekM91leUlRxj8lefH9XH9DN1Ug0QYF7tShjn4FU0iFI4fzQrGAiy38ks6LdapLWfd/bTTCTAgMBAAECggEASAvPStaqKKDQ9KDJ"
        + "gyDdv4YMdiyyTG4COf0Tf8eVYQo0yho9avU4vGX287GwVyPYL44dzj/FIyuRkBaQWzpqSgerfA+lQJk8xb1MhgljykUhPWvFTd40vMR6mlGf6WuVXVu"
        + "kKccut9jQ2VcR4l6l3qZjiAusuz7J4UJidVnDPGZ5FCQ6PaliXvmGKfMnGKeQU+uFpVrjGnIiW2cXiuooiKOfKQGnaKu8mMhkVVcfG32c6e0nFbhl2e"
        + "BiFA5XbaGLyVEnasCYsSFlgj2KQ84Yj9vA9EHj2u9BCj8c+CTlTl3A08dACoBeVTb3nOUWyi2/XC1vE6O+D7IH9/X3Qey+uQKBgQDsQJ1hdfmfM5MNG"
        + "IsiGCPD0YftbOOEpZMwmf6hiunoe9ZJq5kDm0ZcSF8f2yI0+9NL/FF7f92wABVsFwhXE2wXCqzjfLMuJGsfGhlJMYbYxyjzDpGwcSau58CY8N83U2Jo"
        + "wC2rb+PBdjrY1rso+/AKroqcVEhuC5bPQV/b35g3xQKBgQCtMfZm4K3ek/NiRNg29BFMJJ9Eda6fj2gGfnw7KM6/t4ujHIKu9l5+TmdMfi+ObGxTxPzt"
        + "jDzJbMOuAnhnbDggK+pWAsXYh/V19eIHhBV2r4t4u4rUulFnSX3rcd5wUF3gm3/UOFaEABCXDkDltNuWTUajUXte8N4j1/d8J450dwKBgQCLIFoOBO2l"
        + "hTe55JQ61cBqFsBXX2J0wRT28Bj1hNrOn+7Hn5gSeaRrv+rU1bVFQm9iNgq+EcS5WrNC3iV5MeTbhNvfc5YJPEUhIbt2UQY6Lr68s/2zzE6dfbj72A17"
        + "hU1Upqe+Z7vWOAvbmkjJj+QCRls2SKQGuihrkPIkh34aiQKBgGJVzcsXomCPrE6NPB3ncbLD0FH0Z1Nc/vlA85Q7VTfxJK1V0LU6WOhp56i8tI+u68qc"
        + "O+c8Cn9q66du66H9BdjuVjzDkkWg3DM/ijguPGmIvi2sfeTObM2yInZ9zogCeWq/n1KFMkn8J288vogtqceq1IKCDwQWymmbg37Qjsd9AoGBALi6tLHo"
        + "IC07Jj11a70PjuaARtxmL6AbHIjvxuFyyj68f6oQN8VXDDl7c/rTxAEV+q0/3ZVYLWxBHuWf5Dt//KI+MHu3WDkW+EcG4eID4dGpbphSfcTVMqy/majOD"
        + "UXlCpyTYn6iI/rI5BoLPoWhFV4/g7whOM1mouj1uYf5jqT1";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn9XGHgS0oFhn+AreR7jycPMqZ3+cG+gspZUXvk3"
        + "qq364ZWrj1mRL9lFMTOydWH1EyyYvZ9/uRxWBqkuNqPdWq/dY4k/KND4PVG7FJNr8gTMU1DhIZFjdmJdn0OTedpjxZ/TZ+kHq1FQQwuDomxcHBaBdTROVQt"
        + "p6Rs2JMBtAMJ6ao98nRdApKOc3nMPD6gaji9940IY9CadcrzOX1YHU5dUgwbPU1jf6QAU8HugGjlvccn2EtTJy/1j1ZQNB0l27Di5WsU+C8LeuKhnpDPdZX"
        + "lJUcY/JXnx/Vx/QzdVINEGBe7UoY5+BVNIhSOH80KxgIst/JLOi3WqS1n3f200wkwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "/Users/liuzhaoyuan/gitwork/study-hello/logs/";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

