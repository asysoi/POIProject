package com.belcci.carnet.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person extends Party {
	private Passport passport;
	private String firstname = "";
	private String middlename = "";
	private String lastname = "";
	private String efirstname = "";
	private String emiddlename = "";
	private String elastname = "";

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEfirstname() {
		return efirstname;
	}

	public void setEfirstname(String efirstname) {
		this.efirstname = efirstname;
	}

	public String getEmiddlename() {
		return emiddlename;
	}

	public void setEmiddlename(String emiddlename) {
		this.emiddlename = emiddlename;
	}

	public String getElastname() {
		return elastname;
	}

	public void setElastname(String elastname) {
		this.elastname = elastname;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String getName() {
		setName(firstname + " " + middlename + " " + lastname);
		return super.getName();
	}

	@Override
	public String getEname() {
		setEname(efirstname + " " + emiddlename + " " + elastname);
		return super.getEname();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Person party = new Person();
		party.setId(this.getId());
		party.setName(this.getName());
		party.setEname(this.getEname());
		party.setUnp(this.getUnp());
		party.setAddresses(cloneAddresses(this.getAddresses()));
		party.setPhone(this.getPhone() != null ? (Phone) this.getPhone()
				.clone() : null);
		party.setFax(this.getFax() != null ? (Phone) this.getFax().clone()
				: null);
		party.setEmail(this.getEmail());
		party.setBank(this.getBank() != null ? (Party) this.getBank().clone()
				: null);
		party.setAccount(this.getAccount());
		party.setBankcode(this.getBankcode());

		if (this.getBirthday() != null) {
			Calendar bd = GregorianCalendar.getInstance();

			bd.setTimeInMillis(this.getBirthday().getTimeInMillis());
			party.setBirthday(bd);
		} else {
			party.setBirthday(null);
		}

		party.setFirstname(firstname);
		party.setEfirstname(efirstname);
		party.setLastname(lastname);
		party.setElastname(elastname);
		party.setMiddlename(middlename);
		party.setEmiddlename(emiddlename);
		party.setPassport(passport != null ? (Passport) passport.clone() : null);

		return party;
	}

}
