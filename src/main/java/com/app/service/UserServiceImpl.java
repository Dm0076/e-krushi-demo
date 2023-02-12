package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Entity.CombineUserOrder;
import com.app.Entity.Users;
import com.app.Repository.CombineOrderRepository;
import com.app.Repository.UserDao;
import com.app.custom_excs.CustomerHandlingException;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserDao userRepo;
	
	@Autowired
	EmailService emailService;
	
	@Autowired 
	private CombineOrderRepository combineOrderRepository;

	public UserServiceImpl() {
		System.out.println(" in ctor of : "+ getClass().getName());
	}
	
	@Override
	public Users registerOrEditUser(Users user) {
		try
		{
		emailService.sendEmailForNewRegistration(user.getEmail());
		return userRepo.save(user);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
;		}
		return user;
	}

	@Override
	public Users findByEmailAndPassword(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);

	}
	  
	
	@Override
	public CombineUserOrder addCombineitems(CombineUserOrder order, String email) {
		Users users = userRepo.findByEmail(email);
		System.out.println(users.getCity());
		try {
		
		CombineUserOrder combineOrder = order;
		combineOrder.setCouser(users);    //combined order user
		combineOrder.setCity(order.getCity());
		combineOrder.setAddress(order.getAddress());
		combineOrder.setPincode(order.getPincode());
		combineOrder.setEmail(order.getEmail());
		combineOrder.setFirstName(order.getFirstName());
		combineOrder.setLastName(order.getLastName());
		combineOrder.setItemsPrice(order.getItemsPrice());
		combineOrder.setPhone(order.getPhone());
		combineOrder.setProductName(order.getProductName());
		combineOrder.setShippingPrice(order.getShippingPrice());
		combineOrder.setState(order.getState());
		combineOrder.setStatus(order.getStatus());
		combineOrder.setTaxPrice(order.getTaxPrice());
		combineOrder.setTotalPrice(order.getTotalPrice());
		
		return combineOrderRepository.save(combineOrder);
		}
		catch(Exception e)
		{
			System.out.println("============================");
			System.out.println(e.getMessage());
			System.out.println("=============================");
		}
		return null;
	}
	
	@Override
	public List<CombineUserOrder> showConsumerOrder(int id) {
		return combineOrderRepository.findBycustomId(id);
	}
	
	
	//admin controller method 
	
	@Override
	public List<Users> fetchUsers() {
		return userRepo.findAll();
		
	}
	
	public UserServiceImpl (UserDao repo1)
	{
		this.userRepo = repo1;
	}
	
	@Override
	public Users disableUser(Users user, int id) {
		Users us = userRepo.findById(id).orElseThrow( () -> new CustomerHandlingException("User not found"));
		us.setStatus(user.getStatus());
		userRepo.save(us);
		return us;
	}
	@Override
	public Users enableUser(Users user, int id) {
		Users us = userRepo.findById(id).orElseThrow( () -> new CustomerHandlingException("User not found")); 
		us.setStatus(user.getStatus());
		userRepo.save(us);
		return us;
	}
	
	// getting profile info
	@Override
	public Users findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	@Override
	public Users getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
		
	
	@Override
	public void editUserByStatus(Users user) {
		userRepo.editByEmail(user.getStatus(),user.getEmail());
		
	}
	
	@Override
	public Users edit(Users user, int userId) {
		user.setPassword(userRepo.findById(userId).get().getPassword());
		user.setId(userId);
		return userRepo.save(user);
	}
		
	@Override
	public String deleteUserById(Integer uid) {
		userRepo.deleteById(uid);
		return "Deleted User with ID: " + uid + " successfully!";
	}
	
	public void updateUser(Users user) {
		userRepo.save(user);
	}
}
