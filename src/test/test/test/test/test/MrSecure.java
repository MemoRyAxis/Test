package test.test.test.test.test;

import org.apache.commons.codec.digest.DigestUtils;

public class MrSecure {

  public static void main(String[] args) {
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
  }
}
