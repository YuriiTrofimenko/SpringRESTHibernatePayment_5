package org.tyaa.spring.rest.hibernate.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.model.CategoryResponse;
import org.tyaa.spring.rest.hibernate.payment.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PostMapping("/create")
	public CategoryResponse payInstant(@RequestBody Category category) {
		return service.create(category);
	}

	@GetMapping("/get-all")
	public CategoryResponse getAll() {
		return service.getAll();
	}
}
