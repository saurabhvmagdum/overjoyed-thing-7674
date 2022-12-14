package com.masai.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	public static Connection getConnection() throws SQLException {
			
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String url = "jdbc:mysql://localhost:3306/sb101project";
			
			try {
				conn = DriverManager.getConnection(url, "root", "root");
			} 
			catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
			
			
			return conn;
		}
}
