package com.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Employee {

	@Id
	private String employeeId;
	private String employeename;

	@Embedded
	private Company company;

	@Embedded
	private Car car;

	@Version
	private int version;

	public Employee() {

	}

	public Employee(String employeeId, String employeename, Company company, Car car) {
		super();
		this.employeeId = employeeId;
		this.employeename = employeename;
		this.company = company;
		this.car = car;

	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeename=" + employeename + ", company=" + company
				+ ", car=" + car + "]";
	}

}
