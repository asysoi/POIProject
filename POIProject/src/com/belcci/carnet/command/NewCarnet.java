package com.belcci.carnet.command;

import java.util.Map;

import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.CarnetNumber;

public class NewCarnet implements Command {

	public void execute(CarnetATA carnet, Map<String, String> input) {
           carnet = new CarnetATA();
           CarnetNumber number = new CarnetNumber();
           number.setYear(input.get("year"));
           number.setRegion(input.get("region"));
           number.setNumber(Integer.parseInt(input.get("number")));
           carnet.setNumber(number); 
	}

}
