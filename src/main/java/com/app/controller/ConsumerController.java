package com.app.controller;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Time;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.CombineUserOrder;
import com.app.Entity.CropInfo;
import com.app.Entity.Product;
import com.app.Entity.UserPaymentsData;
import com.app.Entity.Users;
import com.app.Repository.PaymentsRepository;
import com.app.Repository.UserDao;
import com.app.service.CropInformServiceImpl;
import com.app.service.EmailService;
import com.app.service.ProductServiceImpl;
import com.app.service.UserServiceImpl;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@CrossOrigin
@RestController
@Transactional
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CropInformServiceImpl cropInformServiceImpl;

	@Autowired
	private UserDao userRepo;

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@Autowired 
	private PaymentsRepository paymentsRepository;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/showProducts")
	public List<Product> getUserDetails() {
		return productServiceImpl.allProducts();

	}

	@GetMapping("/showCropInfo")
	public List<CropInfo> getCropInfo() {
		return cropInformServiceImpl.showCropInfo();
	}

	

	@PostMapping("/profile/{email}")
	public Users consumerProfile(@PathVariable String email) {
		return userRepo.findByEmail(email);
	}



	@PostMapping("/saveCombineOrder/{email}")
	public String saveCombineOrder(@RequestBody CombineUserOrder order, @PathVariable String email) {
		userServiceImpl.addCombineitems(order, email);
		return "Order Saved";
	}

	@GetMapping("/organicProduct")
	public List<Product> organicProduct() {
		return productServiceImpl.findByOrganicProducts();
	}

	@GetMapping("/nonOrganicProduct")
	public List<Product> nonOrganicProduct() {
		return productServiceImpl.findByNonOrganicProducts();
	}

	@GetMapping("/showConsumerOrder/{id}")
	public List<CombineUserOrder> showOrder(@PathVariable int id) {
		return userServiceImpl.showConsumerOrder(id);
	}
	
	
	// //-----------------------------------------------------------------------------------
	// payment gateway razorpay
		//getting  payment info 
		@PostMapping ("/paymentProcess")
		public String paymentProcess (@RequestBody Map <String,Object> data) throws RazorpayException
		{
			//System.out.println(data.get("totalPrice").toString());
			System.out.println("process initiated");
			float amt = Float.parseFloat(data.get("totalPrice").toString());
			//now we create razor pay API starting by importing razorpay
			//in java 11 we can use directly like JS, but we are using Razorpay client
//			var client = new RazorpayClient("rzp_test_huUCFzJNUKwfBG","mz7fDiq408fLEBjkQ24wi6md");
			RazorpayClient client = new RazorpayClient("rzp_test_huUCFzJNUKwfBG","mz7fDiq408fLEBjkQ24wi6md");
			int amt1 =(int) (amt*100);
			//Now we are going to generate order
			
				  JSONObject orderRequest = new JSONObject();
				  orderRequest.put("amount", amt1); // amount in the smallest currency unit
				  orderRequest.put("currency", "INR");
				  orderRequest.put("receipt", "txn:23456");
				  
				
				  //this order is going to razorpay and razorpay's server will generate order id for txn
				  Order order = client.Orders.create(orderRequest);  
				  System.out.println(order);
				  System.out.println(data.get("email").toString());
//				  System.out.println(principal);
//				  System.out.println(principal.getName());
				  //we can save this order object in our database for payment records
				   UserPaymentsData upd = new UserPaymentsData();
				   
				   upd.setAmount((order.get("amount"))+"");
				   upd.setOrderId(order.get("id"));
				   upd.setPaymentId(null);
				   upd.setStatus( order.get("status"));
				  upd.setPayUser(this.userRepo.getUserByEmail(data.get("email").toString()));
				  upd.setReciept(order.get("receipt"));
				  
				 this.paymentsRepository.save(upd);    //payemt data eneterd in dbtable but stsatus is created 
				 
				 // now updating payment record when payment status is captured
				  return order.toString();
		}
		
		// getting payemnt info with payment_id
		@PostMapping("/paymentUpdateProcess")
	     public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data){
			UserPaymentsData updateStatus = this.paymentsRepository.findByOrderId(data.get("order_id").toString());
			updateStatus.setPaymentId(data.get("payment_id").toString());
			updateStatus.setStatus(data.get("status").toString());
			
			String email = updateStatus.getPayUser().getEmail();
			emailService.sendPaymentEmail(email, updateStatus);
			this.paymentsRepository.save(updateStatus);
			System.out.println(data);
			return ResponseEntity.ok("");
		}
	       

}
