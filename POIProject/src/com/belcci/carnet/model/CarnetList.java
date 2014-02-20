package com.belcci.carnet.model;

import java.util.ArrayList;

public class CarnetList extends ArrayList<CarnetATA> {
	private static CarnetList list;

	private CarnetList() {
	}

	public static CarnetList getInstance() {
		if (list == null) {
			list = new CarnetList();
		}
		return list;
	}
}
