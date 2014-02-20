package com.belcci.carnet.command;

import java.util.Map;

import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.Party;

public class AddHolder implements Command {

	public void execute(CarnetATA carnet, Map<String, String> input) {
		Party holder;
		holder = new Party();
		holder.setName(input.get("name"));
		Address address = new Address();
		address.setLine(input.get("line"));
		address.setCountry(CountryList.getInstance()
				.findCountryByName(input.get("country")));
		address.setHouse(input.get("house"));
		address.setIndex(input.get("index"));
		address.setOffice(input.get("office"));
		holder.addAddress(address);
		carnet.setHolder(holder);
	}

}
