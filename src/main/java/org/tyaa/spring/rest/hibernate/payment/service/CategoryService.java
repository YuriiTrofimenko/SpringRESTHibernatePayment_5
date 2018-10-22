package org.tyaa.spring.rest.hibernate.payment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.spring.rest.hibernate.payment.dao.CategoryHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.model.CategoryResponse;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryHibernateDAO dao;

	public CategoryResponse create(Category category) {
		
		dao.create(category);
		CategoryResponse response = new CategoryResponse();
		response.setStatus("success");
		response.setMessage(
			String.format("Категория %s создана", category.getName())
		);
		return response;
	}

	public CategoryResponse getAll() {
		
		CategoryResponse response = new CategoryResponse();
		List<Category> categories = dao.getAll();
		response.setStatus("success");
		response.setData(categories);
		return response;
	}
}
