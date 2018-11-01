package org.tyaa.spring.rest.hibernate.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.spring.rest.hibernate.payment.model.ProductRequest;
import org.tyaa.spring.rest.hibernate.payment.model.ProductResponse;
import org.tyaa.spring.rest.hibernate.payment.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/create")
	public ProductResponse create(@RequestBody ProductRequest productRequest) {
            //TODO
            return service.create(productRequest);
	}
        
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ProductResponse delete(@PathVariable("id") int id) {
            return service.delete(id);
	}

	@GetMapping("/get-all")
	public ProductResponse getAll() {
            return service.getAll();
	}
}
