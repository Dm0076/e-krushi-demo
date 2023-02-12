package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.Entity.UserPaymentsData;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmailForNewRegistration(String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ekrushiseva.ecommerce@gmail.com");
		message.setTo(email);
		message.setSubject("Thank you for registering with us!");
		message.setText("Welcome to the E-krushiSeva ");
		emailSender.send(message);
	}
	
	public void sendPaymentEmail(String email, UserPaymentsData user) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ekrushiseva.ecommerce@gmail.com");
		message.setTo(email);
		message.setSubject("Payment Details!");
		message.setText(user.toString());
		emailSender.send(message);
	}

	public void sendOtp(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Welcome to the E-krushiSeva ");
		message.setText(otp);
		emailSender.send(message);
	}
}
