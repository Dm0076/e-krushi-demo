package com.app.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.Repository.UserDao;

@ExtendWith (MockitoExtension.class)
class UserServiceImplTest {

	@Mock   // we dont want actual data from database
	private UserDao userDao;
	
	// if write autowired actual data will be fetched from database
	private UserServiceImpl userService; 
	
	
	@Test
	void testFetchUsers() {
		
		// fetchUsers is function in userServiceImpl
           userService.fetchUsers(); 
       // verifying from function of   findAll method User   
           verify(userDao).findAll();
		
		// fail("Not yet implemented");
	}
	
	@BeforeEach 
	void setUp()  {
		
		this.userService = new UserServiceImpl(this.userDao);
	}

}