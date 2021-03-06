package one.more.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DoRequest {

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;

		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				System.out.println("Warning: URL Host: " + urlHostName
						+ " vs. " + session.getPeerHost());
				return true;
			}
		};

		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接

			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			trustAllHttpsCertificates();

			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n";
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager, X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

	public static void main(String[] args) throws Exception {
		String str = DoRequest.sendGet( "https://shenpi.100credit.com/tarzan/createFlowRuleController/creatFlowRule.do",
		    "username=审批测试&password=shenpiza&user_name=shenpiza&merchat_name=穆萌测试&api_code=100092&purchase_json={\"auth\":1,\"sensitive\":{\"purchase_flag\":1,\"A0\":0,\"A3\":1,\"A5\":1,\"A6\":1,\"A7\":1,\"B0\":1,\"B3\":1,\"B5\":1,\"B6\":1,\"E1\":0},\"score\":{\"status\":1,\"type\":\"brcreditpoint\",\"level\":1},\"webAction\":1,\"accountChange\":1,\"moreLoan\":{\"A4\":3,\"B4\":3}}&param=2");
		System.out.println("\n" + str);
		// DoRequest.sendGet("http://shenpi.100credit.com/tarzan/createFlowRuleController/creatFlowRule.do",
		// "&username=jinyuanbao_test&password=jinyuanbao_test&user_name=jinyuanbao_test&merchat_name=jinyuanbao_test&api_code=jinyuanbao_test&purchase_json={\"auth\":1,\"sensitive\":{\"purchase_flag\":1,\"A0\":1,\"C1\":1,\"D1\":1,\"E1\":1},\"score\":1}");
	}
}
