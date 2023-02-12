package com.app.service;

import java.util.List;

import com.app.Entity.CombineUserOrder;
import com.app.Entity.Users;

public interface IUserService {

	Users registerOrEditUser(Users user);// R
	
	public Users findByEmailAndPassword(String email, String password);

	public CombineUserOrder addCombineitems(CombineUserOrder order, String email);

	List<CombineUserOrder> showConsumerOrder(int id);

	Users disableUser(Users user, int id);

	Users enableUser(Users user, int id);
	
	String deleteUserById(Integer uid);

	List<Users> fetchUsers();
	
	Users findByEmail(String email);
	
	Users getUserByEmail(String Email);
	
	public void editUserByStatus(Users user);
	
	Users edit(Users user, int userId);
	
	public void updateUser(Users user);

}
