package com.belcci.carnet.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Passport {

	private String personalid ;
	private String code = "";
	private String number = "";
	private Calendar issdate;
	private Calendar validdate;
	private String issued = "";
	private String eissued = "";

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

	public String getEissued() {
		return eissued;
	}

	public void setEissued(String eissued) {
		this.eissued = eissued;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Calendar getIssdate() {
		return issdate;
	}

	public void setIssdate(Calendar issdate) {
		this.issdate = issdate;
	}

	public String getPersonalid() {
		return personalid;
	}

	public void setPersonalid(String personalid) {
		this.personalid = personalid;
	}

	public Calendar getValiddate() {
		return validdate;
	}

	public void setValiddate(Calendar validdate) {
		this.validdate = validdate;
	}

	public String toString() {
		return (code != null ? code : "") + " "
				+ (number != null ? number : "");
	}

	public char[] toXML() {

		return null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Passport pas = new Passport();
		pas.setCode(code);
		pas.setNumber(number);
		pas.setIssued(issued);
		pas.setEissued(eissued);
		pas.setPersonalid(personalid);

		if (issdate != null) {
			Calendar idate = GregorianCalendar.getInstance();
			idate.setTimeInMillis(issdate.getTimeInMillis());
			pas.setIssdate(idate);
		}

		if (validdate != null) {
			Calendar valid = GregorianCalendar.getInstance();
			valid.setTimeInMillis(validdate.getTimeInMillis());
			pas.setValiddate(valid);
		}

		return pas;
	}

}
