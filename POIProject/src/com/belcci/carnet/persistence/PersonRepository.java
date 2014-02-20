package com.belcci.carnet.persistence;
import java.util.ArrayList;
import java.util.List;

import com.belcci.carnet.model.Party;

public class PersonRepository {
	private static final long serialVersionUID = 1L;
	private static PersonRepository repository;
	private List<Party> list = new ArrayList<Party>();

	private PersonRepository() {
	}

	public static PersonRepository getInstance() {
		if (repository == null) {
			repository = new PersonRepository();
		}
		return repository;
	}

	public List<Party> getItems() {
		return list;
	}

	public void setPersons(List<Party> list) {
		this.list = list;
	}
	
	public void addPerson(Party company) {
		if (list == null) {
			list = new ArrayList<Party>();
		}
		list.add(company);
	}
    
	public void set(int index, Party company) {
		if (list != null && index < list.size()) {
			list.set(index, company);
		}
	}
	
	public Party getItem(int index) {
		Party ret = null;
		if (list != null) {
			ret = list.get(index);
		}
		return ret;
	}

	
	
	public int findIndexPersonByName(String name) {
		int index = -1;
		if (name != null) {
			int i = 0;
			for (Party item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					index = i;
					break;
				}
				i++;
			}
		}
		return index;
	}
	
	public Party findPersonByName(String name) {
		Party party = null;
		if (name != null) {
			for (Party item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					party = item;
					break;
				}
			}
		}
		return party;
	}

	public Party findCountryById(int id) {
		Party person = null;
		for (Party item : list) {
			if (item.getId() == id) {
				person = item;
				break;
			}
		}
		return person;
	}

	
	public void sort(boolean byEnglish) {
		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				String fname;
				String sname;
			
				if (byEnglish) {
			        fname = list.get(i).getEname();
			        sname = list.get(j).getEname();
				} else {
					fname = list.get(i).getName();
			        sname = list.get(j).getName();
				}
				
				if (fname.compareToIgnoreCase(sname) > 0) {
					Party person = list.get(i);
					list.set(i, list.get(j));
					list.set(j, person);					
				}
			}
		}
	}
}
