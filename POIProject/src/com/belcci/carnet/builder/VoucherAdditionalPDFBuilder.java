package com.belcci.carnet.builder;

import com.belcci.carnet.model.CarnetATA;

public class VoucherAdditionalPDFBuilder extends PDFBuilder {
	public String getCarnetTextByMap(CarnetATA carnet, String map) {
		String str = "";
		if ("list".equals(map)) {
			str += carnet.getAdditionalListNumber();
		} else if ("number".equals(map)) {
			str = carnet.getNumber().toString();
		} else if ("vouchernumber".equals(map)) {
			str += carnet.getVoucherNumber();
		}
		return str;
	}

}
