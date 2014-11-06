package com.memory.keng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLL {

	public static void main(String[] args) throws Exception {

		/* XML */
		// URL url = new
		// URL("http://192.168.200.174:8080/SCMSmsService/sms/send");
		// String reqStr =
		// "<LIST><MAP ><MOBILE>18612334632</MOBILE><CONTENT>�������ţ�����˭��~</CONTENT><STARTTIME>2014/03/17</STARTTIME><ENDTIME>2014/03/20</ENDTIME><SMSTYPEMAJOR>1000</SMSTYPEMAJOR><SMSTYPESUB>1013</SMSTYPESUB></MAP ></LIST>";

		/* JSON */
		// URL url = new
		// URL("http://192.168.200.174:8080/SCMSmsService/sms/save4map");
		// String reqStr =
		// "{\"MOBILECONTENT\":{\"18612334632\":\"����Ѱ��Ů����ã�������ѣ�һ�����ˣ�ͷ�Լ򵥣���Ŀ���㣬����֪���ŵĸ裬�����ٽ��˵ľ磬���������Σ��������¿㣬��֪���£������ң�����ҡ����ÿ����·���������ĵ�ױ��˵����Ļ�����Լ�������Ȧ�ӡ�������Ļ��п���û�б䡣������û�й������ɣ���ƫ��ì��.\",\"13691512811\":\"����Ѱ��Ů����ã�������ѣ�һ�����ˣ�ͷ�Լ򵥣���Ŀ���㣬����֪���ŵĸ裬�����ٽ��˵ľ磬���������Σ��������¿㣬��֪���£������ң�����ҡ����ÿ����·���������ĵ�ױ��˵����Ļ�����Լ�������Ȧ�ӡ�������Ļ��п���û�б䡣������û�й������ɣ���ƫ��ì��.\"},\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}";

		/* JSON ���һ���� */
		// URL url = new
		// URL("http://192.168.200.174:8080/SCMSmsService/sms/sendSms_");
		// String reqStr =
		// "{\"MOBILE\":[\"18612334632\",\"13691512811\"],\"CONTENT\":\"���ǵ������ӿ�~\",\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}";

		/* XML��Ŷ����� */
		URL url = new URL(
				"http://192.168.200.174:8080/SCMSmsService/sms/sendSms");
		String reqStr = "<LIST><MAP><MOBILE>13691512811!�ǵ�����Ů�ӣ���Ӧ���ɸɾ�����ƯƯ������������·�ϣ�Ҫ�����Լ��������Ĺ��Ȼ������������Ů�ӣ��㲻�ù��ֵ�װ���Լ����������գ��㻹Ҫ�����ʣ���Ҫ���������԰������ܿ����Լ�����Ҫ��������Ҫ���Լ��ں����˾�����ů��</MOBILE><CONTENT></CONTENT><STARTTIME>2014/03/19</STARTTIME><ENDTIME>2014/03/20</ENDTIME><SMSTYPEMAJOR>1000</SMSTYPEMAJOR><SMSTYPESUB>1013</SMSTYPESUB></MAP></LIST>";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		// conn.setRequestProperty("Accept", "application/json");
		// conn.setRequestProperty("User-Agent", "android");
		// conn.setRequestProperty("Accept-Language", "zh-cn");
		// conn.setRequestProperty("Connection", "Keep-Alive");
		// conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);

		OutputStream out = conn.getOutputStream();
		out.write(reqStr.getBytes("UTF-8"));
		out.flush();
		out.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line, resultStr = "";
		while (null != (line = br.readLine())) {
			resultStr += line;
		}
		br.close();
		System.out.println(resultStr);

		System.out.println(conn.getResponseCode());
	}
}
