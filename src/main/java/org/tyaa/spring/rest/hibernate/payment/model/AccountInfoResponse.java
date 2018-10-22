package org.tyaa.spring.rest.hibernate.payment.model;

import java.util.List;

import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.model.AbstractResponse;

public class AccountInfoResponse extends AbstractResponse<List<Category>>{

	private AccountInfo accountInfo;

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}	
}
