package org.tyaa.spring.rest.hibernate.payment.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.tyaa.spring.rest.hibernate.payment.entity.User;

@Repository
public class UserHibernateDAO
	extends AbstractHibernateGeneralizedDAO<User>{

	public User getUserByName(String _userName) {
		User user = null;
		
		CriteriaBuilder builder =
				getSession().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery =
				builder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.where(builder.equal(root.get("name"), _userName));
		Query<User> query = getSession().createQuery(criteriaQuery);
		
		Object userObject = query.uniqueResult();
		if(userObject != null) {
			user = (User)userObject;
		}
		return user;

	}
}
