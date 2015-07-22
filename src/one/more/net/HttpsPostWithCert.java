package one.more.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpsPostWithCert {

	public static void main(String[] args) throws Exception {

		System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
		URL url = new URL(
				"https://sso.100credit.com/serviceValidate?ticket=ST-4-GEhtvAR7zBU7wuCKZwdW-cas&service=http%3A%2F%2Fidata.100credit.com%2Flogin.action");

		URLConnection conn = url.openConnection();
		BufferedReader in;

		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line;
		StringBuffer sb = new StringBuffer(255);

		while ((line = in.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}
