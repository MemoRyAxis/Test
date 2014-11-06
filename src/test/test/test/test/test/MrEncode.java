package test.test.test.test.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * 转换字符串的编码
 */
public class MrEncode {
     
    public static void main(String[] args) throws Exception {
        MrEncode test = new MrEncode();
//        String str = "\u9519\u8bef\u7684\u6765\u6e90";
//        String str = "随行付支付有限公司";
        

        String str = MrEncode.send("09     随行付支付有限公司", "127.0.0.1", 3837);
                 
        System.out.println("str: " + str);
        
        String gbk = test.toGBK(str);
        System.out.println("转换成GBK码: " + gbk);
        
        String iso88591 = test.toISO_8859_1(str);
        System.out.println("转换成ISO-8859-1码: " + iso88591);
        
        String utf8 = test.toUTF_8(str);
        System.out.println("转换成UTF-8码: " + utf8);
        
        gbk = test.changeCharset(iso88591, MrEncode.ISO_8859_1, MrEncode.GBK);
        System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
        
        iso88591 = test.changeCharset(gbk, MrEncode.GBK, MrEncode.ISO_8859_1);
        System.out.println("再把GBK码的字符串转换成ISO_8859_1码: " + iso88591);
        
        utf8 = test.changeCharset(iso88591, MrEncode.ISO_8859_1, MrEncode.UTF_8);
        System.out.println("再把ISO-8859-1码的字符串转换成UTF_8码: " + utf8);
        
        iso88591 = test.changeCharset(gbk, MrEncode.UTF_8, MrEncode.ISO_8859_1);
        System.out.println("再把UTF_8码的字符串转换成ISO_8859_1码: " + iso88591);
        
        gbk = test.changeCharset(utf8, MrEncode.UTF_8, MrEncode.GBK);
        System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
        
        utf8 = test.changeCharset(gbk, MrEncode.GBK, MrEncode.UTF_8);
        System.out.println("再把GBK码的字符串转换成UTF_8码: " + utf8);
        
        /*
        String ascii = test.toASCII(str);
        System.out.println("转换成US-ASCII码: " + ascii);
        gbk = test.changeCharset(ascii, ConverStr.US_ASCII, ConverStr.GBK);
        System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16be = test.toUTF_16BE(str);
        System.out.println("转换成UTF-16BE码:" + utf16be);
        gbk = test.changeCharset(utf16be, ConverStr.UTF_16BE, ConverStr.GBK);
        System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16le = test.toUTF_16LE(str);
        System.out.println("转换成UTF-16LE码:" + utf16le);
        gbk = test.changeCharset(utf16le, ConverStr.UTF_16LE, ConverStr.GBK);
        System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
        System.out.println();
        String utf16 = test.toUTF_16(str);
        System.out.println("转换成UTF-16码:" + utf16);
        gbk = test.changeCharset(utf16, ConverStr.UTF_16LE, ConverStr.GBK);
        System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
        String s = new String("中文".getBytes("UTF-8"), "UTF-8");
        System.out.println(s);
        */
    }
     
    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
    public static final String US_ASCII = "US-ASCII";

    /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /** 8 位 UCS 转换格式 */
    public static final String UTF_8 = "UTF-8";

    /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
    public static final String UTF_16BE = "UTF-16BE";

    /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
    public static final String UTF_16LE = "UTF-16LE";

    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
    public static final String UTF_16 = "UTF-16";

    /** 中文超大字符集 */
    public static final String GBK = "GBK";

	private static Socket sockme;

    /**
     * 将字符编码转换成US-ASCII码
     */
    public String toASCII(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, US_ASCII);
    }

    /**
     * 将字符编码转换成ISO-8859-1码
     */
    public String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, ISO_8859_1);
    }

    /**
     * 将字符编码转换成UTF-8码
     */
    public String toUTF_8(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, UTF_8);
    }

    /**
     * 将字符编码转换成UTF-16BE码
     */
    public String toUTF_16BE(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, UTF_16BE);
    }

    /**
     * 将字符编码转换成UTF-16LE码
     */
    public String toUTF_16LE(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, UTF_16LE);
    }

    /**
     * 将字符编码转换成UTF-16码
     */
    public String toUTF_16(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, UTF_16);
    }

    /**
     * 将字符编码转换成GBK码
     */
    public String toGBK(String str) throws UnsupportedEncodingException {
        return this.changeCharset(str, GBK);
    }

    /**
     * 字符串编码转换的实现方法
     * 
     * @param str
     *            待转换编码的字符串
     * @param newCharset
     *            目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 字符串编码转换的实现方法
     * 
     * @param str
     *            待转换编码的字符串
     * @param oldCharset
     *            原编码
     * @param newCharset
     *            目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
    
    private static String send(String content, String ip, int port) throws Exception {
    	sockme = new Socket(ip, port);
		
		InputStream in = sockme.getInputStream();
		OutputStream out = sockme.getOutputStream();
		
		out.write(content.getBytes());
		byte[] recv = new byte[2048];
		int len = in.read(recv);
		
		String result = new String(recv, 0, len);
		return result;
    }
}
