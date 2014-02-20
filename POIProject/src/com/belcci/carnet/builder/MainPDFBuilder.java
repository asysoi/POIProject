package com.belcci.carnet.builder;

import java.io.IOException;
import java.util.List;

import com.belcci.carnet.config.BoxConfig;
import com.belcci.carnet.config.CarnetPageConfig;
import com.belcci.carnet.config.Stamp;
import com.belcci.carnet.config.TableConfig;
import com.belcci.carnet.model.CarnetATA;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class MainPDFBuilder extends PDFBuilder {
	

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
		} else if ("listammount".equals(map)) {
			str = "";
		}

		return str;
	}
}
