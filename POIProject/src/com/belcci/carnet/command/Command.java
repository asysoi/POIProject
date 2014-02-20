package com.belcci.carnet.command;

import java.util.Map;

import com.belcci.carnet.model.CarnetATA;

public interface Command {
       public void execute(CarnetATA carnet, Map<String,String> input);
}
