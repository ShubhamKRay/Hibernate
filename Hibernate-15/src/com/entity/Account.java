package com.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Account {

	private String accNo;
	private String accountHolderName;
	private String accountHolderAddress;
	private String bankName;
	private String ifsc;

	public Account() {

	}

	public Account(String accNo, String accountHolderName, String accountHolderAddress, String bankName, String ifsc) {
		super();
		this.accNo = accNo;
		this.accountHolderName = accountHolderName;
		this.accountHolderAddress = accountHolderAddress;
		this.bankName = bankName;
		this.ifsc = ifsc;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountHolderAddress() {
		return accountHolderAddress;
	}

	public void setAccountHolderAddress(String accountHolderAddress) {
		this.accountHolderAddress = accountHolderAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", accountHolderName=" + accountHolderName + ", accountHolderAddress="
				+ accountHolderAddress + ", bankName=" + bankName + ", ifsc=" + ifsc + "]";
	}

}
