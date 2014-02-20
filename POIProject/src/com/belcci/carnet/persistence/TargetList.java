package com.belcci.carnet.persistence;

import com.belcci.carnet.model.Target;

public class TargetList extends java.util.ArrayList<Target> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TargetList list;

	private TargetList() {
	}

	public static TargetList getInstance() {
		if (list == null) {
			list = new TargetList();
		}
		return list;
	}
	
	public Target findTargetByName(String name) {
		Target target = null;
		if (name != null) {
			for (Target item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					target = item;
					break;
				}
			}
		}
		return target;
	}

	public Target findTargetById(int id) {
		Target target = null;
		for (Target item : list) {
			if (item.getId() == id) {
				target = item;
				break;
			}
		}
		return target;
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
					Target target = list.get(i);
					list.set(i, list.get(j));
					list.set(j, target);					
				}
			}
		}
	}
}

