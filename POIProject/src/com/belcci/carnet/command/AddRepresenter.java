package com.belcci.carnet.command;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Map;

import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Passport;
import com.belcci.carnet.model.Person;

public class AddRepresenter implements Command {

	public void execute(CarnetATA carnet, Map<String, String> input) {
		Person representer;
		representer = new Person();
		representer.setName(input.get("name"));
		Passport passport = new Passport();
		passport.setCode(input.get("code"));
		passport.setNumber(input.get("number"));
		
		//passport.setIssdate((new GregorianCalendar()).setGregorianChange((new SimpleDateFormat("ddmmyyyy")).parse(input.get("issdate"), new ParsePosition(0))));
		
		representer.setPassport(passport);
		carnet .setRepresenter(representer);
	}
}
