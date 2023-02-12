package com.app.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Users;

public interface UserDao extends JpaRepository<Users, Integer> {

	List<Users> findByFirstName(String firstName);

//find user by email id
	@Query("select u from Users u where u.email= :em")
	public Users findByEmail(@Param("em") String email);

	//find user by email id and password
	@Query("select u from Users u where u.email = ?1 and u.password = ?2")
	Users findByEmailAndPassword(String email, String password);

	//changing user status by email id
	@Transactional
	@Modifying
	@Query("update Users u set u.status=?1 where u.email =?2")
	public void editByEmail(String status, String email);

	//find user by email id	
	@Query("select u from Users u where u.email = ?1")
	public Users getUserByEmail(String email);
	
	@Query("select case when count(u) > 0 then true else false end  from Users u where u.email= :em")
	public Boolean isUsersExistByEmail(@Param("em") String email);

}
