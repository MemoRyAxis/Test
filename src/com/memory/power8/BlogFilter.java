package com.memory.power8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BlogFilter {

	public static void main(String[] args) {
		String blogFileName = "F:\\blog-mg\\_blog-mg_out";
		String regularFileName = "F:\\blog-mg\\regulars";
		File blogFile = new File(blogFileName);
		File regularFile = new File(regularFileName);

		try (InputStream isBlg = new FileInputStream(blogFile);
				InputStream isReg = new FileInputStream(regularFile);
				InputStreamReader isr = new InputStreamReader(isReg);
				BufferedReader brR = new BufferedReader(isr);) {
			byte[] buffer = new byte[1024];
			int size = 0;
			
			int count = 1000;
			while (count-- > 0) {
				size = isBlg.read(buffer);
				System.out.println(new String(buffer, 0, size));
			}

			String tempLine = null;
			while ((tempLine = brR.readLine()) != null) {
				System.out.println(tempLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
