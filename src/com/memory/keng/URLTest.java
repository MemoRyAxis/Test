package com.memory.keng;

import java.io.*;

import java.net.*;

public class URLTest {

	public String load(String url, String query) throws Exception

	{

		URL restURL = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setAllowUserInteraction(false);
		PrintStream ps = new PrintStream(conn.getOutputStream());
		ps.print(query);
		ps.close();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line, resultStr = "";
		while (null != (line = bReader.readLine())) {
			resultStr += line;
		}
		bReader.close();
		return resultStr;
	}

	public static void main(String[] args) {

		try {
			URLTest restUtil = new URLTest();
			String resultString = restUtil
					.load("http://118.144.70.149:28080/smsService/sms/save4map",
							"{\"MOBILECONTENT\":{\"18612334632\":\"这里是帐户余额 账户余额 520这里是本日应扣金额 ,本日应扣金额0 ,这里是什么呢\"},\"SMSTYPEMAJOR\":2000,\"SMSTYPESUB\":2000}");
			System.out.println(resultString);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}