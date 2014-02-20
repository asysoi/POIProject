package com.belcci.carnet.model;

import java.util.Currency;

public class CarnetTotal {
	private double weight;
	private int count;
	private double value;
	private double fee;
	private Currency currency;

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		System.out.println("Set CarnetTotal Value: " + value
				+ " instead old value: " + this.value);
		this.value = value;
		 
	}

	public void empty() {
		setWeight(0d);
		setCount(0);
		setValue(0d);
		setFee(0d);
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		System.out.println("Set CarnetTotal Fee: " + fee
				+ " instead old value: " + this.fee);
		
		this.fee = fee;
     }

}
