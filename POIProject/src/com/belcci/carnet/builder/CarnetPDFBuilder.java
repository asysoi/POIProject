package com.belcci.carnet.builder;

import java.io.FileOutputStream;
import java.io.IOException;

import com.belcci.carnet.config.CarnetPageConfig;
import com.belcci.carnet.config.ConfigReader;
import com.belcci.carnet.config.XMLConfigReader;
import com.belcci.carnet.model.CarnetATA;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CarnetPDFBuilder {
	public static final String FONT = "c:/windows/fonts/ARIAL.TTF";
	private Document document;
	private PdfWriter writer;
	private ConfigReader xreader;
	private CarnetPageConfig pconfig;

	public void createPdf(String filename, CarnetATA carnet,
			String configFileName) throws IOException, DocumentException {
		// step 1
		document = new Document(PageSize.A4, 5, 5, 5, 5);

		// step 2
		writer = PdfWriter.getInstance(document,
				new FileOutputStream(filename));
		// step 3
		document.open();

		// step 4
		xreader = XMLConfigReader.getInstance(configFileName);
		carnet.getIterator().reset();
		createMain(carnet);
		
		for (int vnumber = 1; vnumber <= carnet.getReImportNumber(); vnumber++ ) {
			carnet.getIterator().reset();
			carnet.setVoucherNumber(vnumber);
			createReimport(carnet);
	    }
		
		for (int vnumber = 1; vnumber <= carnet.getReExportNumber(); vnumber++ ) {
			carnet.getIterator().reset();
			carnet.setVoucherNumber(vnumber);
			createReexport(carnet);
	    }

		for (int vnumber = 1; vnumber <= carnet.getTransitNumber(); vnumber++ ) {
			carnet.getIterator().reset();
			carnet.setVoucherNumber(vnumber);
			createTransit(carnet);
	    }

		// step 5
		document.close();
	}
	
	
	private void createTransit(CarnetATA carnet) throws DocumentException, IOException {
		 String pagename = PDFBuilderFactory.PAGE_TRANSIT;
			
			while (carnet.getIterator().hasNext()) {
			    pconfig = xreader.getCarnetPageConfig(pagename);
			    PDFBuilder pmaker = PDFBuilderFactory.getPADFBuilder(pagename);
			    pmaker.createPDFPage(writer, carnet, pconfig);
			    pagename = pconfig.getNextPage();
			    if (pagename.equals(PDFBuilderFactory.PAGE_TRANSITADDITIONAL)) {
			    	carnet.setAdditionalListNumber(carnet.getAdditionalListNumber() + 1); 
			    }
			    document.newPage();
			}
		
	}


	private void createReexport(CarnetATA carnet) throws DocumentException, IOException {
        String pagename = PDFBuilderFactory.PAGE_REEXPORT;
		
		while (carnet.getIterator().hasNext()) {
		    pconfig = xreader.getCarnetPageConfig(pagename);
		    PDFBuilder pmaker = PDFBuilderFactory.getPADFBuilder(pagename);
		    pmaker.createPDFPage(writer, carnet, pconfig);
		    pagename = pconfig.getNextPage();
		    if (pagename.equals(PDFBuilderFactory.PAGE_REEXPORTADDITIONAL)) {
		    	carnet.setAdditionalListNumber(carnet.getAdditionalListNumber() + 1); 
		    }
		    document.newPage();
		}
		
	}


	private void createMain(CarnetATA carnet) throws DocumentException, IOException {
		String pagename = "Main";
		
		while (carnet.getIterator().hasNext()) {
		    pconfig = xreader.getCarnetPageConfig(pagename);
		    PDFBuilder pmaker = PDFBuilderFactory.getPADFBuilder(pagename);
		    pmaker.createPDFPage(writer, carnet, pconfig);
		    pagename = pconfig.getNextPage();
		    if (pagename.equals(PDFBuilderFactory.PAGE_MAINADDITIONAL)) {
		    	carnet.setAdditionalListNumber(carnet.getAdditionalListNumber() + 1); 
		    }
		    document.newPage();
		}
	}
	
	private void createReimport(CarnetATA carnet) throws DocumentException, IOException {
		String pagename = "Reimport";
		
		while (carnet.getIterator().hasNext()) {
		    pconfig = xreader.getCarnetPageConfig(pagename);
		    PDFBuilder pmaker = PDFBuilderFactory.getPADFBuilder(pagename);
		    pmaker.createPDFPage(writer, carnet, pconfig);
		    pagename = pconfig.getNextPage();
		    if (pagename.equals(PDFBuilderFactory.PAGE_REIMPORTADDITIONAL)) {
		    	carnet.setAdditionalListNumber(carnet.getAdditionalListNumber() + 1); 
		    }
		    document.newPage();
		}
	}
}
