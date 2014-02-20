package com.belcci.carnet;

import com.belcci.carnet.form.CarnetApplication;
import com.belcci.carnet.form.TestApplication;

public class CarnetTest {
	
	public static void main(String[] args) {
		CarnetTest ttest = new CarnetTest();
		ttest.testCarnetLoading();
	}
	
	
    public void testCarnetLoading() {
    	
    	CarnetApplication app = new com.belcci.carnet.form.CarnetApplication();
    	//TestApplication app = new com.belcci.carnet.form.TestApplication();
    	app.open();
    }    
}