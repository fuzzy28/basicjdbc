package org.jrue.poc.jdbc.constants;

public class AppConstants {

	public static final String SQL_INSERT = 
			"INSERT INTO M_ACCOUNT (USERCD,USERNAME,PASSWORD,CASHIERPASSWORD,EMPLOYEEID,DELFLAG) " +
			"VALUES (?, ? ,? ,? ,?, ?)";
	
	public static final String SQL_CONNECTION_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	public static final String SQL_CONNECTION_URL = "jdbc:oracle:thin:@172.16.137.9:1521:orcl";
	
	public static final String SQL_CONNECTION_USERNAME = "db_prod_backup";
	
	public static final String SQL_CONNECTION_PASSWORD = "password123";
	
	public static final int SQL_CONNECTION_POOL_INIT_SIZE = 10;

}
