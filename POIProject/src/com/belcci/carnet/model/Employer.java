package com.belcci.carnet.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employer {
	private Job position;
	private Person person;
	private String trust;

	public Job getPosition() {
		return position;
	}

	public void setPosition(Job position) {
		this.position = position;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getTrust() {
		return trust;
	}

	public void setTrust(String trust) {
		this.trust = trust;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Employer party = new Employer();
		party.setPerson(person);
		party.setPosition(position);
		
		/*
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
		party.setTrust(this.getTrust());
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

		party.setFirstname(this.getFirstname());
		party.setEfirstname(this.getEfirstname());
		party.setLastname(this.getLastname());
		party.setElastname(this.getElastname());
		party.setMiddlename(this.getMiddlename());
		party.setEmiddlename(this.getEmiddlename());
		party.setPassport(this.getPassport() != null ? (Passport) this.getPassport().clone() : null);

		party.setPosition(position != null ? position : null);
		*/ 
		return party;
	}
}
