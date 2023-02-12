package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_history")

public class UserPaymentsData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long myOrderId; // for database table identity

	private String orderId;

	private String amount;

	private String reciept;

	private String status;

	@ManyToOne
	private Users payUser;

	// after payement captured payment id will be generated by razor pay
	private String paymentId;

	@Override
	public String toString() {
		return "orderId = " + orderId + "\n" + "amount = " + amount + "\n" + "reciept = " + reciept + "\n" + "status = "
				+ status + "\n" + "paymentId = " + paymentId + "\n\n\n" + "Thank You..!";
	}

}
