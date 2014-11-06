package com.memory.liud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection conn = null;

	private String dbURL = "";
	private String dbName = "";
	private String dbPassword = "";

	public DBConnection(String dbURL, String dbName, String dbPassword) {
		super();
		this.dbURL = dbURL;
		this.dbName = dbName;
		this.dbPassword = dbPassword;
	}

	/**
	 * open connection
	 * 
	 * @author ma_qz
	 * @date 2014年4月27日下午3:04:06
	 */
	public void openConn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbName, dbPassword);
			System.out.println("connection open.(" + dbURL + ")");
		} catch (Exception e) {
			System.out.println("\t\t\topen connection failed.");
			System.out.println("\t\t\tdbURL: " + dbURL);
		}
	}

	/**
	 * execute query sql
	 * 
	 * @author ma_qz
	 * @date 2014年4月27日下午3:07:38
	 */
	public ResultSet executeQuery(String sql) {
		statement = null;
		resultSet = null;
		try {
			if (conn.isClosed())
				return null;
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("\t\t\texecute query failed.");
			System.out.println("\t\t\tsql: " + sql);
		}
		return resultSet;
	}

	/**
	 * execute update sql
	 * 
	 * @author ma_qz
	 * @date 2014年4月27日下午5:40:49
	 */
	public boolean updateQuery(String sql) {
		boolean flag = false;
		statement = null;
		resultSet = null;
		try {
			if (conn.isClosed())
				return flag;
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sql);
			flag = true;
		} catch (Exception e) {
			System.out.println("\t\t\texecute query failed.");
			System.out.println("\t\t\tsql: " + sql);
		}
		return flag;
	}

	/**
	 * close connection
	 * 
	 * @author ma_qz
	 * @date 2014年4月27日下午3:03:50
	 */
	public void closeConn() throws Exception {
		try {
			if (conn.isClosed())
				return;
			conn.close();
			System.out.println("connection close.(" + dbURL + ")");
		} catch (Exception e) {
			System.out.println("\t\t\tclose connection failed.");
		}
	}
}
