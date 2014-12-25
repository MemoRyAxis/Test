package com.memory.power8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class About12306 {

	public static void main(String[] args) {
		
		File file = new File("F:\\blog-mg\\dssss.txt");

		try (InputStream is = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(is, "GB2312");
				BufferedReader brR = new BufferedReader(isr);) {
			
			int lineNumber = 0;
			StringBuffer result = new StringBuffer();
			String tempLine = null;
			while ((tempLine = brR.readLine()) != null) {
				String reg1 = "6590011992";
				String reg2 = "6590011993";
				if (tempLine.contains(reg1) || tempLine.contains(reg2)) {
					result.append(lineNumber);
					result.append(',');
					System.out.println(tempLine);
				}
				lineNumber ++;
			}
			System.out.println();
			System.out.println();
			System.out.println("--->\t result: " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
