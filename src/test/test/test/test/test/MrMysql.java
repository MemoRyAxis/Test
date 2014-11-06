package test.test.test.test.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MrMysql {

	public static void main(String[] args) throws Exception {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/agent";
		String user = "root";
		String password = "MemoRyAxis";

		Class.forName(driver);

		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn.isClosed());
		conn.close();
	}
}
