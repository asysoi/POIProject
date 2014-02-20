package com.belcci.carnet.model;

public class Address {
	private int id;
	private Country country;
	private String city = "";                                    
	private String ecity = "";
	private String index  = "";
	private String line = "";
	private String eline = "";
	private String house = "";
	private String office = "";
	private AddressType type; 
    
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEcity() {
		return ecity;
	}

	public void setEcity(String ecity) {
		this.ecity = ecity;
	}

	public String getEline() {
		return eline;
	}

	public void setEline(String eline) {
		this.eline = eline;
	}

	public String toString() {
		return index + ", " + house + ", " + line + ", " + office + ", " + city
				+ ", " + country;
	}

	
	public String toXML() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append("<address>");
		strbuffer.append("<country>");
		strbuffer.append(country != null ? country.toXML() : "");
		strbuffer.append("</country>");
		strbuffer.append("<index>");
		strbuffer.append(index);
		strbuffer.append("</index>");
		strbuffer.append("<line>");
		strbuffer.append(line);
		strbuffer.append("</line>");
		strbuffer.append("<house>");
		strbuffer.append(house);
		strbuffer.append("</house>");
		strbuffer.append("<office>");
		strbuffer.append(office);
		strbuffer.append("</office>");
		strbuffer.append("<city>");
		strbuffer.append(city);
		strbuffer.append("</city>");
		strbuffer.append("</address>");
		return strbuffer.toString();
	}
	
	public Object clone() throws CloneNotSupportedException {
		Address adr = new Address();
		adr.setCity(city);
		adr.setCountry(country);
		adr.setEcity(ecity);
		adr.setEline(eline);
		adr.setHouse(house);
		adr.setIndex(index);
		adr.setLine(line);
		adr.setOffice(office);
		adr.setId(id);
		adr.setType(type);
		return adr;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}


}
