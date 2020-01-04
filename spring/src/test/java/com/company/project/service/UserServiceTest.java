package com.company.project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testFindByEmail() {
		User user = userService.findByEmail("yijie.zou@nyu.edu");
		Assert.assertNotNull(user);
	}

	@Test
	@Transactional
	public void testAddUser() {
		Assert.assertEquals("5c44bc791444836cbba355ed1b5753bc", userService.addUser("testtest@nyu.edu", "123456", "PATIENT"));
	}

}
