package com.belcci.carnet.builder;

import java.io.IOException;
import java.util.List;

import com.belcci.carnet.config.BoxConfig;
import com.belcci.carnet.config.CarnetPageConfig;
import com.belcci.carnet.config.Stamp;
import com.belcci.carnet.config.TableConfig;
import com.belcci.carnet.model.CarnetATA;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class MainAdditionalPDFBuilder extends PDFBuilder {
  		
	public String getCarnetTextByMap(CarnetATA carnet, String map) {
		String str = "";

		if ("list".equals(map)) {
	       str +=  carnet.getAdditionalListNumber();  
		} else if ("number".equals(map)) {
			str = carnet.getNumber().toString();
		} 
		return str;
	}
}
