package com.poseidon.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	private static DBConn dbConn = new DBConn();
	
	public static DBConn getInstance() {
		return dbConn;
	}
	
	public Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url ="jdbc:mariadb://220.70.33.29:3306/trip";
			con = DriverManager.getConnection(url,"trip","01234567");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
