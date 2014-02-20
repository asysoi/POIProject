package com.belcci.carnet.model;

public class IDGenerator {
	private int id;

	public int getNextID() {
		return id++; 
	}

	public static void setID(int id) {
	}
}
