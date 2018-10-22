package org.tyaa.spring.rest.hibernate.payment.dao.interfaces;

import java.util.List;

import org.tyaa.spring.rest.hibernate.payment.entity.Payment;

public interface IPaymentDAO {

	public String payNow(Payment payment);
	
	public List<Payment> getTransactionInfo(String vendor);
}
