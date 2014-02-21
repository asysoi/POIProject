package com.belcci.carnet.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Company extends Party {
	private Employer director;
	private Employer presenter;
	private Employer reciever;
	
	public Employer getDirector() {
		return director;
	}
	public void setDirector(Employer director) {
		this.director = director;
	}
	public Employer getPresenter() {
		return presenter;
	}
	public void setPresenter(Employer presenter) {
		this.presenter = presenter;
	}
	public Employer getReciever() {
		return reciever;
	}
	public void setReciever(Employer reciever) {
		this.reciever = reciever;
	}
	
	public char[] toXML() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append(super.toXML());
		strbuffer.append(director != null ? director.getPerson().toXML() : ""); 
		strbuffer.append(presenter != null ? presenter.getPerson().toXML() : "");
		strbuffer.append(reciever != null ? reciever.getPerson().toXML() : "");
		return strbuffer.toString().toCharArray();
	}
      
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Company party = new Company();
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

		party.setDirector(director != null ? (Employer) director.clone() : null);
		party.setReciever(reciever != null ? (Employer) reciever.clone() : null);
		party.setPresenter(presenter != null ? (Employer) presenter.clone() : null);         

		return party;
	}
}
