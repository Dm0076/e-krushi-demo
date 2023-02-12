package com.app.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.Entity.Users;

@SpringBootTest
class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
     
	
	
	@Test
	void testIsUsersExistByEmail() {
		


		Users user = new Users("Rohit","Bombarde","rohit@gmail.com","batsman","7897899143","Prustal+zolo", (long) 411041,"pune","MH","Active","ADMIN");
		userDao.save(user);
		
		
	    Boolean actualResult = userDao.isUsersExistByEmail("rohit@gmail.com");
	    
	    assertThat(actualResult).isTrue();
		//fail("Not yet implemented");
	  
	}
	
	
//	// testing getuserbyemail method 
//	@Test
//	void testGetUserByEmail()
//	{
//		Users user1 = new Users("Devyani","Bombarde","devnikhil@gmail.com","Teacher","7897899143","Prustal+zolo", (long) 411041,"pune","MH","Active","ADMIN");
//         userDao.save(user1);
//		System.out.println(user1);
//		       Users actualUserObject = userDao.findByEmail("devnikhil@gmail.com");
//		       System.out.println(actualUserObject);
//		       System.out.println("in testGetByemail");
//		       assertThat(actualUserObject).usingRecursiveComparison().isEqualTo(user1);
//	}
//	
	@AfterEach 
	  void tearDown () {
		
		System.out.println("Tearing Down ");
	      	  
	}
	
	@BeforeEach 
	void setUp()
	{
	       System.out.println("set UP");	
	}

}