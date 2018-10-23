package org.tyaa.spring.rest.hibernate.payment.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyaa.spring.rest.hibernate.payment.dao.interfaces.IPaymentDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Payment;

@Repository
public class PaymentHibernateDAO implements IPaymentDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public String payNow(Payment payment) {
		getSession().save(payment);
		return "Payment successfull with amount : " + payment.getAmount();
	}

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}

	@Override
	public List<Payment> getTransactionInfo(String _vendor) {
		
		CriteriaBuilder builder =
				getSession().getCriteriaBuilder();
		CriteriaQuery<Payment> criteriaQuery =
				builder.createQuery(Payment.class);
		Root<Payment> root = criteriaQuery.from(Payment.class);
		criteriaQuery.where(builder.equal(root.get("vendor"), _vendor));
		Query<Payment> query = getSession().createQuery(criteriaQuery);
		
		return query.list();

	}

}
