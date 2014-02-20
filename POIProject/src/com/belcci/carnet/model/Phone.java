package com.belcci.carnet.model;

public class Phone {
	private int id;
	private int type;
	private String wCode  = "";
	private String cCode = "";
	private String number = "";
	private String ext = "";

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getwCode() {
		return wCode;
	}

	public void setwCode(String wCode) {
		this.wCode = wCode;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Phone ph = new Phone();
		ph.setId(id);
		ph.setType(type);
		ph.setwCode(wCode);
		ph.setcCode(cCode);
		ph.setNumber(number);
		ph.setExt(ext);
		return ph;
	}

	
	
}
