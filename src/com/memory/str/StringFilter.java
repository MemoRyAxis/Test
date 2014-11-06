package com.memory.str;

public class StringFilter {

	/**
	 * filter repeated characters in the string
	 * 
	 * @return string made up with different character
	 * @author ma_qz
	 * @date 2014年4月30日上午1:32:37
	 */
	public static String doFilter(String inputString) {
		String outputString = "";
		for (int i = 0; i < inputString.length(); i++) {
			if (!charInString(inputString.charAt(i), outputString))
				outputString += inputString.charAt(i);
		}
		return outputString;
	}

	/**
	 * determines if the string has the character
	 * 
	 * @return true if the string has this character , false otherwise
	 * @author ma_qz
	 * @date 2014年4月30日上午1:26:53
	 */
	private static boolean charInString(char c, String str) {
		if (str == null || str.length() == 0)
			return false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String s1 = "deefd";
		String s2 = "afafafaf";
		String s3 = "pppppppp";

		System.out.println(doFilter(s1));
		System.out.println(doFilter(s2));
		System.out.println(doFilter(s3));
	}
}
