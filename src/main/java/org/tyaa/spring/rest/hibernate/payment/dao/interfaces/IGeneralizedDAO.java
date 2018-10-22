package org.tyaa.spring.rest.hibernate.payment.dao.interfaces;

import java.util.List;

public interface IGeneralizedDAO<T> {

	public T create(T entity);
	public T get(Integer entity_id);
	public T edit(T entity);
	public void remove(T entity);
	
	public List<T> getAll();
}
