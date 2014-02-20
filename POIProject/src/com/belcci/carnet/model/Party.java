package com.belcci.carnet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class Party {
	private int id;
	private String name = "";
	private String ename = "";
	private String unp  = "";
	private List<Address> addresses = new ArrayList<Address>();
	private Phone phone;
	private Phone fax;
	private String email  = "";
	private String trust  = "";
	private Party bank;
	private String account  = "";
	private String bankcode  = "";
	private Calendar birthday;

	public String getUnp() {
		return unp;
	}

	public void setUnp(String unp) {
		this.unp = unp;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Phone getFax() {
		return fax;
	}

	public void setFax(Phone fax) {
		this.fax = fax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		if (addresses == null) {
			addresses = new ArrayList<Address>();
		}
		addresses.add(address);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] toXML() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append("<name>");
		strbuffer.append(name);
		strbuffer.append("</name>");
		strbuffer.append("<ename>");
		strbuffer.append(ename);
		strbuffer.append("</ename>");
		strbuffer.append("<unp>");
		strbuffer.append(unp);
		strbuffer.append("</unp>");
		strbuffer.append("<phone>");
		strbuffer.append(phone);
		strbuffer.append("</phone>");
		strbuffer.append("<fax>");
		strbuffer.append(fax);
		strbuffer.append("</fax>");
		strbuffer.append("<email>");
		strbuffer.append(email);
		strbuffer.append("</email>");
		for (Address addr : addresses) {
			strbuffer.append(addr.toXML());
		}

		return strbuffer.toString().toCharArray();
	}

	public String getTrust() {
		return trust;
	}

	public void setTrust(String trust) {
		this.trust = trust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Party getBank() {
		return bank;
	}

	public void setBank(Party bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Object clone() throws CloneNotSupportedException {
	    Party party = new Party();
	    party.setId(id);
		party.setName(name);
		party.setEname(ename);
		party.setUnp(unp);
		party.setAddresses(cloneAddresses(addresses));
		party.setPhone(phone  != null ? (Phone) phone.clone() : null);
		party.setFax(fax != null ? (Phone) fax.clone() : null);
		party.setEmail(email);
		party.setTrust(trust);
		party.setBank(bank != null ? (Party) bank.clone() : null);
		party.setAccount(account);
		party.setBankcode(bankcode);
		Calendar bd = GregorianCalendar.getInstance();
		bd.setTimeInMillis(birthday.getTimeInMillis());
		party.setBirthday(bd);
		return party;
	}

	protected List<Address> cloneAddresses(List<Address> adrlist) {
		List<Address> adrs = new ArrayList<Address>();
		
		for(Address adr: adrlist) {
			try {
				adrs.add((Address) adr.clone());
			} catch(Exception ex) {
				adrs.add(null);
			}
		}
		return adrs;
	}

    
}
