package com.wallet.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WalletDaoUtility {
	public static Connection getConnectionToMysql() {
		
		Connection connection=null;
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ford_schema?"+"user=root&password=root");
			System.out.println("Connection to Mysql successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return connection;
	}
}
