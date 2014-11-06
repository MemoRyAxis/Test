package com.memory.keng;

public class Charset {

	public static void main(String[] args) throws Exception {

		String testStr = "凡俗往来总能寻见真情18612334632";

//		 byte[] b1 = testStr.getBytes("UTF-8");
//		 byte[] b1 = testStr.getBytes("GBK");
//		byte[] b1 = testStr.getBytes("ISO-8859-1");
		byte[] b1 = testStr.getBytes();

		System.out.println(b1.length);
		for (int i = 0; i < b1.length; i++) {
			System.out.print(b1[i]);
			System.out.print(' ');
		}
	}
}
