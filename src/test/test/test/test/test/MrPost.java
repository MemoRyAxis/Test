package test.test.test.test.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("deprecation")
public class MrPost {

	private static HttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) throws Exception {
		// String spdb1 =
		// "http://localhost:8080/deposit_server/application/receive.do?json={'bankName_Action':'SpdbUpload','sendDate':'20141128','sendTime':'180002','startDate':'20140701','endDate':'20140707','tableName':'ProvisionUnpaidDetail','ref':'1','clearDate':'2014','initDate':'2013','accountId':'4','prefix':'T1-10_','content':'10000025041027, 10000025041028, 10000025041029, 10000025041030, 10000025041031, 10000025041032, 10000025041033'}";
		// MrPost.get(spdb1);
		// MrPost.get("http://www.baidu.com");
		HeaderBean headerBean = new HeaderBean();
		headerBean.setBankName_Action("SpdbUpload");
		MrPost.post(
				"http://localhost:8080/deposit_server/application/receive.do",
				headerBean);
	}

	public static void get(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);

		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			String entityStr = EntityUtils.toString(httpEntity);

			System.out.println(entityStr);
			System.out.println(httpResponse.toString());

			EntityUtils.consume(httpEntity);
		}
		System.out.println(statusCode);

		httpGet.releaseConnection();
	}

	public static void post(String url, Object... objects) throws Exception {
		HttpPost httpPost = new HttpPost(url);

		String[] jsons = new String[objects.length];
		StringBuffer jsonStr = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			String tempJsonStr = JSON.toJSONString(objects[i]);
			jsons[i] = tempJsonStr;
			jsonStr.append(tempJsonStr);
		}

		NameValuePair nvp = new BasicNameValuePair("json", jsonStr.toString());
		List<NameValuePair> paramsList = new ArrayList<>();
		paramsList.add(nvp);

		httpPost.setEntity(new UrlEncodedFormEntity(paramsList, HTTP.UTF_8));

		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode == HttpStatus.SC_OK) {
			System.out.println("ok");
		}
	}

}

class HeaderBean {
	private String bankName_Action; // 银行名称和行为 为分哪个service执行
	private String sendDate; // 发送时间，即当前时间 年月日
	private String sendTime; // 时分秒
	private String startDate; // 文件的起始时间
	private String endDate; // 文件的结束时间
	private String clearDate; // 清算日期
	private String initDate; // 初始时间
	private String tableName; // 表名
	private String ref; // 流水号
	private String accountId; // 银行信息id
	private String prefix; // 后缀 1-1
	private String content; // 内容

	public String getBankName_Action() {
		return bankName_Action;
	}

	public void setBankName_Action(String bankName_Action) {
		this.bankName_Action = bankName_Action;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getClearDate() {
		return clearDate;
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
