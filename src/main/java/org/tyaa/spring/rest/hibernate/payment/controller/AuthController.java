package org.tyaa.spring.rest.hibernate.payment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.entity.Role;
import org.tyaa.spring.rest.hibernate.payment.entity.User;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;
import org.tyaa.spring.rest.hibernate.payment.model.AccountInfo;
import org.tyaa.spring.rest.hibernate.payment.model.AccountInfoResponse;
import org.tyaa.spring.rest.hibernate.payment.model.CategoryResponse;
import org.tyaa.spring.rest.hibernate.payment.model.UserRequest;
import org.tyaa.spring.rest.hibernate.payment.service.AuthService;
import org.tyaa.spring.rest.hibernate.payment.service.CategoryService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private HttpSession httpSession;
	
	@Autowired
	private AuthService service;

	@PostMapping("/create/role")
	public AbstractResponse<Object> createRole(@RequestBody Role _role) {
		return service.createRole(_role);
	}

	@PostMapping("/create/user")
	public AbstractResponse<Object> createUser(@RequestBody UserRequest _userRequest) {
                _userRequest.setRole_id(1);
		return service.createUser(_userRequest);
	}
	
	@PostMapping("/signin")
	public AccountInfoResponse signin(@RequestBody UserRequest _userRequest) {
		AccountInfoResponse response =
				service.getAccountInfoResponse(_userRequest);
		if (response.getAccountInfo() != null) {
			httpSession.setAttribute(
					"ACCOUNT_INFO"
					, ((AccountInfoResponse)response).getAccountInfo()
				);
		}
		return response;
	}
	
	@PostMapping("/signout")
	public AbstractResponse<Object> signout() {
		
		httpSession.removeAttribute("ACCOUNT_INFO");
		return service.signOut();
	}
	
	@PostMapping("/check")
	public AccountInfo check() {
		return null;
	}
	
	/*@GetMapping("/get-all")
	public CategoryResponse getAll() {
		return service.getAll();
	}*/
}
