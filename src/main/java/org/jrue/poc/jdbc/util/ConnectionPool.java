package org.jrue.poc.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jrue.poc.jdbc.constants.AppConstants;

/**
 * Singleton implementation of Connection Pooling
 * 
 * @author jruelos
 * @since 1.6.2015
 * 
 */
public class ConnectionPool {

	private BasicDataSource datasource;
	
	private ConnectionPool() {
		datasource = new BasicDataSource();
		datasource.setDriverClassName(AppConstants.SQL_CONNECTION_DRIVER);
		datasource.setUrl(AppConstants.SQL_CONNECTION_URL);
		datasource.setUsername(AppConstants.SQL_CONNECTION_USERNAME);
		datasource.setPassword(AppConstants.SQL_CONNECTION_PASSWORD);
		datasource.setInitialSize(AppConstants.SQL_CONNECTION_POOL_INIT_SIZE);
	}
	
	private static class INSTANCE_HOLDER {
		private static final ConnectionPool connectionInstance = new ConnectionPool();
	}
	
	public static Connection getConnection() throws SQLException {
		return INSTANCE_HOLDER.connectionInstance.datasource.getConnection();
	}
}