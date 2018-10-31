package org.tyaa.spring.rest.hibernate.payment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.spring.rest.hibernate.payment.dao.CategoryHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.dao.ProductHibernateDAO;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.entity.Product;
import org.tyaa.spring.rest.hibernate.payment.model.CategoryResponse;
import org.tyaa.spring.rest.hibernate.payment.model.ProductResponse;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductHibernateDAO dao;

	public ProductResponse create(Product product) {
		
		dao.create(product);
		ProductResponse response = new ProductResponse();
		response.setStatus("success");
		response.setMessage(
			String.format("Product %s created", product.getName())
		);
		return response;
	}
        
        public ProductResponse delete(int productId) {
		
                Product product = dao.get(productId);
		dao.remove(product);
		ProductResponse response = new ProductResponse();
		response.setStatus("success");
		response.setMessage(
			String.format("Product %s deleted", product.getName())
		);
		return response;
	}

	public ProductResponse getAll() {
		
		ProductResponse response = new ProductResponse();
		List<Product> products = dao.getAll();
		response.setStatus("success");
		response.setData(products);
		return response;
	}
}
