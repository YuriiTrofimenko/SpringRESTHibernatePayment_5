package org.tyaa.spring.rest.hibernate.payment.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.spring.rest.hibernate.payment.dao.PaymentHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Payment;
import org.tyaa.spring.rest.hibernate.payment.model.PaymentResponse;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentHibernateDAO dao;

	public PaymentResponse pay(Payment payment) {
		payment.setPaymentDate(new Date());
		String message = dao.payNow(payment);
		PaymentResponse response = new PaymentResponse();
		response.setStatus("success");
		response.setMessage(message);
		response.setTxDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a").format(new Date()));
		return response;
	}

	public PaymentResponse getTx(String vendor) {
		PaymentResponse response = new PaymentResponse();
		List<Payment> payments = dao.getTransactionInfo(vendor);
		response.setStatus("succes");
		response.setPayments(payments);
		return response;
}
}
