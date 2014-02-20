package com.belcci.carnet.load;

import com.belcci.carnet.model.CarnetATA;

public interface CarnetReader {
	    final String[] rheaders = { "¹", "Type", "Quantity", "weight", "goods",
	            "Country" };
	   
		public void setFilePath(String filename);
		public void loadCarnetATA(CarnetATA carnet, String fileName);
		public CarnetATA loadCarnetATA(String filename);
}
