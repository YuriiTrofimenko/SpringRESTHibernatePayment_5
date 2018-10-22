package org.tyaa.spring.rest.hibernate.payment.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyaa.spring.rest.hibernate.payment.dao.interfaces.IGeneralizedDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;

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
		// TODO Auto-generated method stub
		return getSession().createCriteria(Category.class).list();
	}
}
