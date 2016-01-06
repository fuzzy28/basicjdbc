package org.jrue.poc.jdbc;

import java.sql.SQLException;


import org.jrue.poc.jdbc.util.ConnectionPool;
import org.junit.Assert;
import org.junit.Test;
public class ConnectionPoolTest {

	@Test
	public void testConnectivity() throws SQLException {
		Assert.assertNotNull(ConnectionPool.getConnection());
	}
}
