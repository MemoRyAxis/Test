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
		// "<LIST><MAP ><MOBILE>18612334632</MOBILE><CONTENT>我是王桥，你是谁咧~</CONTENT><STARTTIME>2014/03/17</STARTTIME><ENDTIME>2014/03/20</ENDTIME><SMSTYPEMAJOR>1000</SMSTYPEMAJOR><SMSTYPESUB>1013</SMSTYPESUB></MAP ></LIST>";

		/* JSON */
		// URL url = new
		// URL("http://192.168.200.174:8080/SCMSmsService/sms/save4map");
		// String reqStr =
		// "{\"MOBILECONTENT\":{\"18612334632\":\"做个寻常女生最好，三五好友，一个恋人，头脑简单，面目清秀，听街知巷闻的歌，看老少皆宜的剧，买少量首饰，穿舒适衣裤，鲜知世事，出父家，进夫家。穿好看的衣服。化少许的淡妆。说得体的话语，有自己的生活圈子。身体里的还有颗心没有变。对世界没有过多质疑，不偏颇矛盾.\",\"13691512811\":\"做个寻常女生最好，三五好友，一个恋人，头脑简单，面目清秀，听街知巷闻的歌，看老少皆宜的剧，买少量首饰，穿舒适衣裤，鲜知世事，出父家，进夫家。穿好看的衣服。化少许的淡妆。说得体的话语，有自己的生活圈子。身体里的还有颗心没有变。对世界没有过多质疑，不偏颇矛盾.\"},\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}";

		/* JSON 多号一内容 */
		// URL url = new
		// URL("http://192.168.200.174:8080/SCMSmsService/sms/sendSms_");
		// String reqStr =
		// "{\"MOBILE\":[\"18612334632\",\"13691512811\"],\"CONTENT\":\"这是第三个接口~\",\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}";

		/* XML多号多内容 */
		URL url = new URL(
				"http://192.168.200.174:8080/SCMSmsService/sms/sendSms");
		String reqStr = "<LIST><MAP><MOBILE>13691512811!记得你是女子，你应当干干净净，漂漂亮亮，你走在路上，要相信自己是最美的姑娘，然后再忘记你是女子，你不该过分的装饰自己，除了容颜，你还要有气质；你要懂得自尊自爱，不能看轻自己；你要善良，你要让自己在乎的人觉得温暖。</MOBILE><CONTENT></CONTENT><STARTTIME>2014/03/19</STARTTIME><ENDTIME>2014/03/20</ENDTIME><SMSTYPEMAJOR>1000</SMSTYPEMAJOR><SMSTYPESUB>1013</SMSTYPESUB></MAP></LIST>";
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
