package org.tyaa.spring.rest.hibernate.payment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.spring.rest.hibernate.payment.entity.Role;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;
import org.tyaa.spring.rest.hibernate.payment.model.AccountInfo;
import org.tyaa.spring.rest.hibernate.payment.model.AccountInfoResponse;
import org.tyaa.spring.rest.hibernate.payment.model.UserRequest;
import org.tyaa.spring.rest.hibernate.payment.service.AuthService;
import org.tyaa.spring.rest.hibernate.payment.servletfilter.SecurityFilter;

@RestController
@RequestMapping("/api/auth")
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
                _userRequest.setRole_id(SecurityFilter.CUSTOMER_ROLE_ID);
		return service.createUser(_userRequest);
	}
	
	@PostMapping("/signin")
	public AccountInfoResponse signin(@RequestBody UserRequest _userRequest) {
		AccountInfoResponse response =
				service.getAccountInfoResponse(_userRequest);
		if (response.getData() != null) {
			httpSession.setAttribute(
					"ACCOUNT_INFO"
					, ((AccountInfoResponse)response).getData()
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
