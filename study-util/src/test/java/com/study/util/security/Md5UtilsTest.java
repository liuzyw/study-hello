package com.study.util.security;

import java.security.MessageDigest;
import java.util.Arrays;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created on 2017-10-14
 *
 * @author liuzhaoyuan
 */
public class Md5UtilsTest {

    @Test
    public void test() throws Exception{
            //用于加密的字符
            char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };

            String pwd = "liu";
            try {
                //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
                byte[] btInput = pwd.getBytes();

                //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
                MessageDigest mdInst = MessageDigest.getInstance("MD5");

                //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
                mdInst.update(btInput);

                // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
                byte[] md = mdInst.digest();

                // 把密文转换成十六进制的字符串形式
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {   //  i = 0
                    byte byte0 = md[i];  //95
                    str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                    str[k++] = md5String[byte0 & 0xf];   //   F
                }

                //返回经过加密后的字符串
                System.out.println(new String(str));
            } catch (Exception e) {
        }
        System.out.println(DigestUtils.md5Hex(pwd).toUpperCase());


    }

    @Test
    public void testBase64(){
        String aa = Base64.encode("abcdefg");
        System.out.println(aa);
        String bb = Base64.decodeStr(aa);
        System.out.println(bb);
    }


}
