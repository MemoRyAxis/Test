package test.test.test.test.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class MrSecure {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("123456      : " + DigestUtils.md5Hex("123456"));
        System.out.println("111111      : " + DigestUtils.md5Hex("111111"));
        System.out.println("123456      : " + DigestUtils.md5Hex("123456	"));
        System.out.println("123456ab    : " + DigestUtils.md5Hex("123456ab"));
        System.out.println("123456ab    : " + DigestUtils.md5Hex("br123654"));
        System.out.println("mqz.13      : " + DigestUtils.md5Hex("mqz.13"));
        System.out.println("br123654    : " + DigestUtils.md5Hex("br123654"));
        System.out.println("100credit   : " + DigestUtils.md5Hex("100credit"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));

        // File mr = new
        // File("E:\\workspace0319\\Test\\src\\test\\test\\test\\test\\test\\CopyOfMrSecure.java");
        // File mr2 = new
        // File("E:\\workspace0319\\Test\\src\\test\\test\\test\\test\\test\\CopyOfCopyOfMrSecure.java");
        // System.out.println("mrSecure    : " + DigestUtils.md5Hex(new FileInputStream(mr)));
        // System.out.println("mrSecure    : " + DigestUtils.md5Hex(new FileInputStream(mr2)));

        Base64 base64 = new Base64();
        String str = "123456";
        String strSure = base64.encodeAsString(str.getBytes());
        System.out.println(strSure);
        byte[] strDeSure = base64.decode(strSure.getBytes());
        System.out.println(new String (strDeSure));

    }
}
