package org.jrue.poc.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.jrue.poc.jdbc.model.User;
import org.jrue.poc.jdbc.service.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

	private UserRepository repository;
	
	@Before
	public void init() {
		repository = new UserRepository();
	}
	
	@After
	public void destroy() {
		repository = null;
	}
	
	@Test
	public void testSingleCRUD() {
		//test insert single record
		User user = new User();
		user.setUserCode("JRUELOS");
		user.setUserName("JOEL RUELOS");
		user.setPassword("password123");
		user.setEmployeeId("123312");
		repository.save(user);
		
		//test query single record
		Assert.assertNotNull(repository.find(user.getUserCode()));
		
		//test update single record
		user.setUserName("JOEL RUELOS JR");
		user.setPassword("password12345");
		user.setEmployeeId("12331245");
		repository.update(user);
		
		User updatedUser = repository.find(user.getUserCode()).get(0);
		
		Assert.assertEquals(updatedUser.getUserName(), updatedUser.getUserName());
		Assert.assertEquals(user.getPassword(), updatedUser.getPassword());
		Assert.assertEquals(user.getEmployeeId(), updatedUser.getEmployeeId());
		
		//test logical delete
		repository.delete(user);		
		Assert.assertTrue(repository.find(user.getUserCode()).isEmpty());
	}
	
	@Test
	public void testBatchCRUD() {		
		//test batch insert
		List<User> users = new ArrayList<User>();
		User john = new User();
		john.setUserCode("JOHN");
		john.setUserName("JOHN JOHN");
		john.setEmployeeId("123456");
		john.setPassword("P@ssw0rd123");		
		users.add(john);
		
		User michael = new User();
		michael.setUserCode("MICHAEL");
		michael.setUserName("JOHN MICHAEL");
		michael.setEmployeeId("654321");
		michael.setPassword("P@ssw0rd456");
		users.add(michael);
		
		Assert.assertTrue(repository.save(users));
		
		//test newly inserted two records
		Assert.assertNotNull(repository.find(john.getUserCode()));
		Assert.assertNotNull(repository.find(michael.getUserCode()));
	}
}