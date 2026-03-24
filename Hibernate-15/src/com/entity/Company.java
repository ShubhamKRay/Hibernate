package com.entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Company {

	private String registrationNo;
	private String companyName;
	private String policy;

	@Embedded
	private Account account;

	public Company() {

	}

	public Company(String registrationNo, String companyName, String policy, Account account) {
		super();
		this.registrationNo = registrationNo;
		this.companyName = companyName;
		this.policy = policy;
		this.account = account;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Company [registrationNo=" + registrationNo + ", companyName=" + companyName + ", policy=" + policy
				+ ", account=" + account + "]";
	}

}
