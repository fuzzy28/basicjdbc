package org.jrue.poc.jdbc;

import java.sql.SQLException;


import org.jrue.poc.jdbc.util.ConnectionPool;
import org.junit.Assert;
import org.junit.Test;
public class ConnectionPoolTest {
	
	static String a;	
	private ConnectionPoolTest() {
		a = "Test";
	}

	public static void main(String[] args) {

		System.out.println(a);
	}
}