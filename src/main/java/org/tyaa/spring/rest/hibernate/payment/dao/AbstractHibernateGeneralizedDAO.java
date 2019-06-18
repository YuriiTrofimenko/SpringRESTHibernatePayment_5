package org.tyaa.spring.rest.hibernate.payment.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.tyaa.spring.rest.hibernate.payment.dao.interfaces.IGeneralizedDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Payment;

public abstract class AbstractHibernateGeneralizedDAO<T>
	implements IGeneralizedDAO<T>{
	
	@Autowired
	private SessionFactory factory;
	
	protected Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}

	@Override
	public T create(T entity) {
		getSession().save(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Integer entity_id) {
		Class<T> entityBeanType =
				((Class<T>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0]);
		return (T) getSession().get(entityBeanType, entity_id);
	}

	@Override
	public T edit(T entity) {
		getSession().update(entity);
		return entity;
	}

	@Override
	public void remove(T entity) {
		// TODO Auto-generated method stub
		getSession().delete(entity);;
	}

	@Override
	public List<T> getAll() {
		
		@SuppressWarnings("unchecked")
		Class<T> entityBeanType =
				((Class<T>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0]);
		
		CriteriaBuilder builder =
				getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery =
				builder.createQuery(entityBeanType);
		Root<T> root = criteriaQuery.from(entityBeanType);
		criteriaQuery.select(root);
		Query<T> query = getSession().createQuery(criteriaQuery);
                // query.setFirstResult(0); SKIP
                // query.setMaxResults(0); TAKE
		return query.list();
	}
}
