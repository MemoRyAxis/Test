package com.memory.keng;

//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.PostMethod;

public class URLLL {
/*
	public static void main(String[] args) throws Exception {

		
		 String reqStr =
		 "{\"MOBILECONTENT\":{\"18612334632\":\"做个寻常女生最好，三五好友，一个恋人，头脑简单，面目清秀，听街知巷闻的歌，看老少皆宜的剧，买少量首饰，穿舒适衣裤，鲜知世事，出父家，进夫家。穿好看的衣服。化少许的淡妆。说得体的话语，有自己的生活圈子。身体里的还有颗心没有变。对世界没有过多质疑，不偏颇矛盾.\",\"13691512811\":\"做个寻常女生最好，三五好友，一个恋人，头脑简单，面目清秀，听街知巷闻的歌，看老少皆宜的剧，买少量首饰，穿舒适衣裤，鲜知世事，出父家，进夫家。穿好看的衣服。化少许的淡妆。说得体的话语，有自己的生活圈子。身体里的还有颗心没有变。对世界没有过多质疑，不偏颇矛盾.\"},\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}";
		 System.out.println(reqStr);
		 
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(
				"http://192.168.200.174:8080/SCMSmsService/sms/save4map");
//		 "http://192.168.200.174:8080/SCMSmsService/sms/send");
//	"http://192.168.200.174:8080/smsService/sms/show?id=2014031710021690");
//				"http://192.168.31.42:8082/posmgr/repayApply.do?method=listRepayApply");
//		NameValuePair n1 = new NameValuePair("MERC_SN", "836427073110001");
//		post.addParameter(n1);
		
//		post.setRequestHeader("Accept-Language", "en-us,en;q=0.5");
//		post.setRequestHeader("Accept-Encoding", "gzip, deflate");
//		post.setRequestHeader(
//				"User-Agent",
//				"Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.9.0.7) Gecko/2009021910 Firefox/3.0.7 (.NET CLR 3.5.30729)");
//		post.addRequestHeader("Content-Type",
//				"application/x-www-form-urlencoded;charset=utf-8");
		post.addRequestHeader("Content-Type", "text/plain; charset=utf-8");
//		post.setRequestBody("<LIST><MAP><MOBILE>18612334632</MOBILE><CONTENT>ni2工hao</CONTENT><STARTTIME>2014/03/17</STARTTIME><ENDTIME>2014/03/20</ENDTIME><SMSTYPEMAJOR>1000</SMSTYPEMAJOR><SMSTYPESUB>1013</SMSTYPESUB></MAP></LIST>");
		post.setRequestBody("{\"MOBILECONTENT\":{\"18612334632\":\"这里是帐户余额 账户余额 0这里是本日应扣金额 ,本日应扣金额0 ,这里是什么呢\",\"18612334632\":\"这里是帐户余额 账户余额 0这里是本日应扣金额 ,本日应扣金额0 ,这里是什么呢\",\"18612334632\":\"这里是帐户余额 账户余额 0这里是本日应扣金额 ,本日应扣金额0 ,这里是什么呢\",\"18612334632\":\"这里是帐户余额 账户余额 0这里是本日应扣金额 ,本日应扣金额0 ,这里是什么呢\"},\"SMSTYPEMAJOR\":1000,\"SMSTYPESUB\":1013}");

		System.out.println(httpClient.executeMethod(post));
		System.out.println(post.getResponseBodyAsString());
	}*/
}
