package com.belcci.carnet.persistence;

import java.util.ArrayList;
import java.util.List;

import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Person;

public class CompanyRepository {
    private List<Party> list = new ArrayList<Party>();
    private static CompanyRepository repository;
        
    private CompanyRepository() {
    }
    
    public static CompanyRepository getInstance() {
    	if (repository == null) {
    		repository = new CompanyRepository();
    	}
    	return repository;
    }

	public List<Party> getItems() {
		return list;
	}

	public void setCompanies(List<Party> list) {
		this.list = list;
	}
	
	public void addCompany(Party company) {
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
	
	public Party findCompanyByName(String name) {
		Party ret = null;
		for(Party company: list) {
			if (name.equals(company.getName())) {
				ret = company;
				break;
			}
		}
		return ret;
	}
	
	public int findIndexCompanyByName(String name) {
		int index = -1;
		if (name != null) {
			int i = 0;
			for (Party company : list) {
				if (name.equals(company.getName()) || name.equals(company.getEname())) {
					index = i;
					break;
				}
				i++;
			}
		}
		return index;
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
					Party party = list.get(i);
					list.set(i, list.get(j));
					list.set(j, party);					
				}
			}
		}
	}
}
