package com.belcci.carnet.model;

public class CarnetNumber {
    private String region;
    private String year;
    private int number;
    
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString() {
		return "BY/" + region + "/" + year + "/" + convertIntegerToString(number);
	}
	
	private String convertIntegerToString(int num) {
        String str = "" + 10000 + num ; 
		return str.substring(2);
	}

      
}
