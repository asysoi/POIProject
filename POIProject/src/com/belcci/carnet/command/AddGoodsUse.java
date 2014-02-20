package com.belcci.carnet.command;

import java.util.Map;

import com.belcci.carnet.model.CarnetATA;

public class AddGoodsUse implements Command {

	public void execute(CarnetATA carnet, Map<String, String> input) {
	       carnet.setUsing(input.get("using"));	
	}
}
