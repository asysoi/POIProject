package com.belcci.carnet.load;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.ProductItem;

public class WordReader implements CarnetReader {
	private String filepath;

	public void setFilePath(String filename) {
		filepath = filename;
	}

	private XWPFDocument openDocument() {
		XWPFDocument document = null;
		try {
			FileInputStream fis = new FileInputStream(filepath);
			document = new XWPFDocument(fis);
		} catch (Exception e) {
			System.out.println("FIle can't be opened. Please check its format.");
		}
		return document;
	}

	private XWPFTable findGoodsTable(XWPFDocument document) {
		XWPFTable table = null;

		if (document != null) {

			int firstRow = 0;

			try {
				List<IBodyElement> elements = document.getBodyElements();
				for (IBodyElement element : elements) {

					if (element instanceof XWPFTable) {
						table = (XWPFTable) element;

						if (table.getNumberOfRows() > 0) {
							XWPFTableRow row = table.getRow(firstRow);
							List<XWPFTableCell> rowcells = row.getTableCells();

							for (int j = 0; j < rowcells.size(); j++) {
								XWPFTableCell xwpfTableCell = rowcells.get(j);
								System.out.print(xwpfTableCell.getText()
										+ "  ->   ");

								if (xwpfTableCell.getText()
										.indexOf(rheaders[j]) == -1) {
									table = null;
									break;
								} else {
									System.out.print(xwpfTableCell.getText()
											.indexOf(rheaders[j]) + "  |  ");
								}
							}
							System.out.println();
						}
					}
					if (table != null)
						break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return table;
	}

	public void loadCarnetATA(CarnetATA carnet) {
		XWPFDocument document = openDocument();
		System.out.println("Document is " + document);
		XWPFTable table = findGoodsTable(document);
		carnet.emptyGoods();

		if (table != null) {
			System.out.println("Table: " + table.getNumberOfRows() + " .  ");

			for (int i = 1; i < table.getNumberOfRows(); i++) {
				XWPFTableRow row = table.getRow(i);
				List<XWPFTableCell> rowcells = row.getTableCells();
				ProductItem product = new ProductItem();
				try {

					product.setFirstNumber(rowcells.get(0).getText());
					product.setDescription(rowcells.get(1).getText());
					product.setCount(Integer
							.parseInt(rowcells.get(2).getText()));
					product.setWeight(Double.parseDouble(rowcells.get(3)
							.getText()));
					product.setValue(Double
							.parseDouble(rowcells.get(4).getText()));
					String code = rowcells.get(5).getText();
					product.setCountry(CountryList.getInstance().findCountryByCode(code));
					// if (!(product.getFirstNumber().isEmpty() &&
					// product.getDescription().toUpperCase().indexOf("итого".toUpperCase())
					// == 0 )) {
					carnet.addProductItem(product);
					// }
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} else {
			System.out
					.println("Carnet ATA table isn't found in this document. Please check source file...");
		}
	}

	@Override
	public CarnetATA loadCarnetATA(String filename) {
		this.setFilePath(filename);
		CarnetATA carnet = new CarnetATA();
		loadCarnetATA(carnet);
		return carnet; 
	}
	
	@Override
	public void loadCarnetATA(CarnetATA carnet, String filename) {
		setFilePath(filename);
		loadCarnetATA(carnet);
	}
	
	
	
	public void loadHWPFDocument(String fileName) throws IOException {
		InputStream fis = new FileInputStream(fileName);
		POIFSFileSystem fs = new POIFSFileSystem(fis);
		HWPFDocument doc = new HWPFDocument(fs);

		Range range = doc.getRange();

		for (int i = 0; i < range.numParagraphs(); i++) {
			Paragraph par = range.getParagraph(i);
			System.out.println(par.text());
		}
		Paragraph tablePar = range.getParagraph(0);
		if (tablePar.isInTable()) {
			Table table = range.getTable(tablePar);
			for (int rowIdx = 0; rowIdx < table.numRows(); rowIdx++) {
				TableRow row = table.getRow(rowIdx);
				System.out.println("row " + (rowIdx + 1) + ",is table header: "
						+ row.isTableHeader());
				for (int colIdx = 0; colIdx < row.numCells(); colIdx++) {
					TableCell cell = row.getCell(colIdx);
					System.out.println("column " + (colIdx + 1) + ",text= "
							+ cell.getParagraph(0).text());
				}
			}
		}
	}

	
	
}
