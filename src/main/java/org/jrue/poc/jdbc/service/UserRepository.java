package org.jrue.poc.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jrue.poc.jdbc.constants.AppConstants;
import org.jrue.poc.jdbc.constants.LogicalDelete;
import org.jrue.poc.jdbc.model.User;
import org.jrue.poc.jdbc.util.ConnectionPool;

public class UserRepository extends Repository<User> implements BatchRepository<User>{

	
	@Override
	public List<User> find(String key) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		
		List<User> users = new ArrayList<User>();
		try {
			connection = ConnectionPool.getConnection();
			final boolean hasParameter = (key != null && key.length() > 0);
			String query = String.format("SELECT USERCD,USERNAME,PASSWORD,EMPLOYEEID FROM M_ACCOUNT %s", 
					hasParameter ? "WHERE USERCD = ? AND DELFLAG = ?" : "");
			statement = connection.prepareStatement(query);
			
			if(hasParameter) {
				statement.setString(1, key);
				statement.setInt(2, LogicalDelete.NORMAL.getFlag());
			}
			
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setUserCode(resultSet.getString("USERCD"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setEmployeeId(resultSet.getString("EMPLOYEEID"));	
				users.add(user);
			}
			
		} catch (SQLException e) {
			users = null;
			e.printStackTrace();
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		
		return users;
	}
	
	@Override
	public boolean save(User record) {
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement insertStatement = null;		
		try {
			connection = ConnectionPool.getConnection();
			insertStatement = connection.prepareStatement(AppConstants.SQL_INSERT);
			insertStatement.setString(1, record.getUserCode());
			insertStatement.setString(2, record.getUserName());
			insertStatement.setString(3, record.getPassword());
			insertStatement.setString(4, record.getPassword());
			insertStatement.setString(5, record.getEmployeeId());
			insertStatement.setInt(6, LogicalDelete.NORMAL.getFlag());			
			isSuccessful = insertStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(insertStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean update(User record) {		
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement updateStatement = null;
		try {
			connection = ConnectionPool.getConnection();
			updateStatement = connection.prepareStatement(
							"UPDATE M_ACCOUNT SET USERNAME = ?,PASSWORD = ?,CASHIERPASSWORD = ?,EMPLOYEEID = ?,DELFLAG = ? " +
							",UPDPERSON = ? WHERE USERCD = ?");
			updateStatement.setString(1, record.getUserName());
			updateStatement.setString(2, record.getPassword());
			updateStatement.setString(3, record.getPassword());
			updateStatement.setString(4, record.getEmployeeId());
			updateStatement.setInt(5, LogicalDelete.NORMAL.getFlag());	
			updateStatement.setString(6, record.getUserCode());					
			updateStatement.setString(7, record.getUserCode());			
			isSuccessful = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(updateStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	
	@Override
	public boolean delete(User record) {
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement updateStatement = null;
		try {
			connection = ConnectionPool.getConnection();			
			updateStatement = connection.prepareStatement(
							"UPDATE M_ACCOUNT SET DELFLAG = ? WHERE USERCD = ?");
			updateStatement.setInt(1, LogicalDelete.DELETED.getFlag());			
			updateStatement.setString(2, record.getUserCode());		
			isSuccessful = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(updateStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean save(List<User> record) {
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement insertStatement = null;
		try {
			connection = ConnectionPool.getConnection();
			insertStatement = connection.prepareStatement(AppConstants.SQL_INSERT);
			
			for (User user : record) {
				insertStatement.setString(1, user.getUserCode());
				insertStatement.setString(2, user.getUserName());
				insertStatement.setString(3, user.getPassword());
				insertStatement.setString(4, user.getPassword());
				insertStatement.setString(5, user.getEmployeeId());
				insertStatement.setInt(6, LogicalDelete.NORMAL.getFlag());	
				insertStatement.addBatch();
			}
				
			isSuccessful = insertStatement.executeBatch().length == record.size();
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(insertStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean update(List<User> record) {
		// TODO Auto-generated method stub
		return false;
	}
}
