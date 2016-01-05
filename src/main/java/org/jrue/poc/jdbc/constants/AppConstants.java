package org.jrue.poc.jdbc.constants;

public class AppConstants {

	public static final String SQL_INSERT = 
			"INSERT INTO M_ACCOUNT (USERCD,USERNAME,PASSWORD,CASHIERPASSWORD,EMPLOYEEID,DELFLAG) " +
			"VALUES (?, ? ,? ,? ,?, ?)";
}
