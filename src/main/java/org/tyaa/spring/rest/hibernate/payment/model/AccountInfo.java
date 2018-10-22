package org.tyaa.spring.rest.hibernate.payment.model;

public class AccountInfo {

	public String userName;
	public Integer roleId;

	public AccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountInfo(String userName, Integer roleId) {
		super();
		this.userName = userName;
		this.roleId = roleId;
	}
}
