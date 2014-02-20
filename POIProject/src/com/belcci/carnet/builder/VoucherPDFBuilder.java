package com.belcci.carnet.builder;

import com.belcci.carnet.model.CarnetATA;

public class VoucherPDFBuilder extends PDFBuilder {
	public String getCarnetTextByMap(CarnetATA carnet, String map) {
		String str = "";
		if ("holder".equals(map)) {
			if (carnet.getHolder() != null) {
			   str = carnet.getHolder().getName()
				+ carnet.getHolder().getAddresses().get(0).toString();
			}
		} else if ("representer".equals(map)) {
			if (carnet.getRepresenter() != null) {
			  str = carnet.getRepresenter().getName();
			}
		} else if ("use".equals(map)) {
			str = carnet.getUsing();
		} else if ("number".equals(map)) {
			str = carnet.getNumber().toString();
		}  else if ("vouchernumber".equals(map)) {
			str +=  carnet.getVoucherNumber();
		}	
		return str;
	}
}
