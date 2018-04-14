package network.programming.io;

import com.study.util.security.Base64;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created on 2018-03-29
 *
 * @author liuzhaoyuan
 */
public class IOStreamTest {

    public static void main(String[] args) {

        String fileName = "/Users/liuzhaoyuan/Desktop/aa.txt";

        bbb(fileName);
//        aaa(fileName);
    }

    private static void bbb(String fileName) {
        try {
            InputStream is = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(is, sha);
            byte[] bytes = new byte[10];
            int len = 0;
            while ((len = digestInputStream.read(bytes)) != -1) {

            }
            digestInputStream.close();
            is.close();
            byte[] digest = sha.digest();

            System.out.println(Base64.encode(digest));
        } catch (Exception e) {

        }
    }

    private static void aaa(String fileName) {
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(fileName), 15)) {

            byte[] bytes = new byte[10];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }

        } catch (IOException e) {

        }
    }

}
