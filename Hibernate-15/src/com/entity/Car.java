package com.entity;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Car {

	private String carNo;
	private String carModel;
	private LocalDateTime localDateTime;
	private double price;

	public Car() {

	}

	public Car(String carNo, String carModel, LocalDateTime localDateTime, double price) {
		super();
		this.carNo = carNo;
		this.carModel = carModel;
		this.localDateTime = localDateTime;
		this.price = price;
	}

	
	
	
	
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [carNo=" + carNo + ", carModel=" + carModel + ", localDateTime=" + localDateTime + ", price="
				+ price + "]";
	}

}
