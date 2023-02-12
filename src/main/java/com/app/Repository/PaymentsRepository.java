package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Entity.UserPaymentsData;

public interface PaymentsRepository extends  JpaRepository<UserPaymentsData, Long> {

	public UserPaymentsData findByOrderId(String orderId);
}
