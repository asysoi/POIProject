package com.belcci.carnet.model;

public class ProductItem {
	private int id;
    private String firstNumber; 
    private double weight;
    private int count;
    private double value;
    private Country country;
    private String description  = "";
    private String edescription = "";
    private boolean isModified = false;
    
	public String getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(String firstNumber) {
		if (! firstNumber.equals(this.firstNumber)) {
			setModified(true);
		}
		this.firstNumber = firstNumber;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		if ( weight != this.weight) {
			setModified(true);
		}
		this.weight = weight;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		if ( count != this.count) {
			setModified(true);
		}
		this.count = count;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		if ( value != this.value) {
			setModified(true);
		}
		this.value = value;
	}
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		System.out.println("Country :" + country);
		if (this.country!=null) {	
			setModified(!this.country.equals(country));
		} else {
			setModified(country!=null);
		}
		this.country = country;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if (!description.equals(this.description)) {
			setModified(true);
		}
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "[firstNumber=" + firstNumber + ", description=" + description
				+ ", count=" + count + ", weight=" + weight +", values=" + value 
				+ ", country=" 	+ country +  "]";
	}
	
	public boolean isModified() {
		return isModified;
	}
	
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEdescription() {
		return edescription;
	}
	public void setEdescription(String edescription) {
		this.edescription = edescription;
	}
    
}
