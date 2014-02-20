package com.belcci.carnet.persistence;

import com.belcci.carnet.model.ProductType;

public class ProductTypeList extends java.util.ArrayList<ProductType> {
	private static final long serialVersionUID = 1L;
	private static ProductTypeList list;

	private ProductTypeList() {
	}

	public static ProductTypeList getInstance() {
		if (list == null) {
			list = new ProductTypeList();
		}
		return list;
	}
	
	public ProductType findProductTypeByName(String name) {
		ProductType type = null;
		if (name != null) {
			for (ProductType item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					type = item;
					break;
				}
			}
		}
		return type;
	}

	public ProductType findProductTypeById(int id) {
		ProductType type = null;
		for (ProductType item : list) {
			if (item.getId() == id) {
				type = item;
				break;
			}
		}
		return type;
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
					ProductType type = list.get(i);
					list.set(i, list.get(j));
					list.set(j, type);					
				}
			}
		}
	}
}

