package com.belcci.carnet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryList extends java.util.ArrayList<Country> {
	private static CountryList list;

	private CountryList() {
	}

	public static CountryList getInstance() {
		if (list == null) {
			list = new CountryList();
		}
		return list;
	}

	public Country findCountryByName(String name) {
		Country country = null;
		if (name != null) {
			for (Country item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					country = item;
					break;
				}
			}
		}
		return country;
	}

	public Country findCountryById(int id) {
		Country country = null;
		for (Country item : list) {
			if (item.getId() == id) {
				country = item;
				break;
			}
		}
		return country;
	}

	public Country findCountryByCode(String code) {
		Country country = null;
		if (code != null) {
			for (Country item : list) {
				if (code.equals(item.getCode())) {
					country = item;
					break;
				}
			}
		}
		return country;
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
					Country country = list.get(i);
					list.set(i, list.get(j));
					list.set(j, country);					
				}
			}
		}
	}
}
