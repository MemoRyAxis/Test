package test.test.test.test.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MrURL {

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        System.out.println(URLDecoder.decode("%E5%AE%A2%E6%88%B7%E5%8E%9F%E5%8D%95%E4%BD%8D%E8%80%81%E6%9D%BF%20%E5%B7%B2%E4%B8%89%E5%B9%B4%E4%BB%A5%E4%B8%8A%E6%9C%AA%E8%81%94%E7%B3%BB%E3%80%82%E6%9C%AA%E8%83%BD%E6%8F%90%E4%BE%9B%E6%96%B0%E8%81%94%E7%B3%BB%E6%96%B9%E5%BC%8F%E3%80%82", "utf-8"));
        System.out.println(URLDecoder.decode("%E6%97%A0%E6%B3%95%E6%8B%A8%E5%8F%B7"));
    }
}
