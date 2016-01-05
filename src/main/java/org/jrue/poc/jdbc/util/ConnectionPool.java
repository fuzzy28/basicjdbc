package org.jrue.poc.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	private static volatile Connection connection = null;
		
	private ConnectionPool() {}
		
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			synchronized (ConnectionPool.class) {
				if(connection == null) {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.137.9:1521:orcl","db_prod_backup","password123");
				}
			}
		}
		return connection;
	}
}
