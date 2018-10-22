package org.tyaa.spring.rest.hibernate.payment.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.entity.Payment;
import org.tyaa.spring.rest.hibernate.payment.entity.Role;
import org.tyaa.spring.rest.hibernate.payment.entity.User;

@Repository
public class UserHibernateDAO
	extends AbstractHibernateGeneralizedDAO<User>{

	public User getUserByName(String _userName) {
		User user = null;
		Object userObject =
				getSession().createCriteria(User.class)
					.add(Restrictions.eq("name", _userName))
					.uniqueResult();
		if(userObject != null) {
			user = (User)userObject;
		}
		return user;

	}
}
