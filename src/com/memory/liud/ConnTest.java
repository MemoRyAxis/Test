package com.memory.liud;

import java.sql.ResultSet;

public class ConnTest {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String name = "root";
		String password = "sys406";

		DBConnection dbc = new DBConnection(url, name, password);
		dbc.openConn();

		ResultSet rs = dbc.executeQuery("select * from test;");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + rs.getInt("id"));
		}
		boolean flag = dbc.updateQuery("insert into t_user(u_name, u_password) values ('root', 'memoi')");
		if(flag) {
			System.out.println("insert success");
		}

		dbc.closeConn();
	}
}
