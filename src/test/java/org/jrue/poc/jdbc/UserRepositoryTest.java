package org.jrue.poc.jdbc;

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
	public void testCRUD() {
		User user = new User();
		user.setUserCode("JRUELOS");
		user.setUserName("JOEL RUELOS");
		user.setPassword("password123");
		user.setEmployeeId("123312");
		repository.save(user);
		
		Assert.assertNotNull(repository.find(user.getUserCode()));
		
		user.setUserName("JOEL RUELOS JR");
		user.setPassword("password12345");
		user.setEmployeeId("12331245");
		repository.update(user);
		
		User updatedUser = repository.find(user.getUserCode()).get(0);
		
		Assert.assertEquals(updatedUser.getUserName(), updatedUser.getUserName());
		Assert.assertEquals(user.getPassword(), updatedUser.getPassword());
		Assert.assertEquals(user.getEmployeeId(), updatedUser.getEmployeeId());
		
		repository.delete(user);		
		Assert.assertTrue(repository.find(user.getUserCode()).isEmpty());
	}
}