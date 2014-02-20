package com.belcci.carnet.builder;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.belcci.carnet.config.BoxConfig;
import com.belcci.carnet.config.CTCell;
import com.belcci.carnet.config.CarnetPageConfig;
import com.belcci.carnet.config.ImageBox;
import com.belcci.carnet.config.Stamp;
import com.belcci.carnet.config.TableConfig;
import com.belcci.carnet.form.CarnetApplication;
import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.CarnetIterator;
import com.belcci.carnet.model.CarnetTotal;
import com.belcci.carnet.model.ProductItem;
import com.belcci.numerals.Numerals;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.VerticalText;

public abstract class PDFBuilder {
	public static final String WINDIR = "windir";
	private static final String CHAR_PARAGRAPH = "\n";

	public void createPDFPage(PdfWriter writer, CarnetATA carnet,
			CarnetPageConfig pconfig) throws DocumentException, IOException {

		List<BoxConfig> textboxes = pconfig.getTextboxes();
		List<TableConfig> tables = pconfig.getTables();
		List<Stamp> stamps = pconfig.getStamps();
		List<BoxConfig> boxes = pconfig.getBoxes();
		List<BoxConfig> outputs = pconfig.getOutputs();
		List<ImageBox> images = pconfig.getImages();

		if (boxes != null) {
			for (int i = 0; i < boxes.size(); i++) {
				BoxConfig box = boxes.get(i);
				if (box.getColorFill() == 0f) {
					makeBorderedBoxtInAbsolutePosition(writer, box);
				} else {
					makeFilledBorderedBoxtInAbsolutePosition(writer, box);
				}
			}
		}

		if (textboxes != null) {
			for (int i = 0; i < textboxes.size(); i++) {
				BoxConfig box = textboxes.get(i);

				if (box.isVertical()) {
					makeVerticalTextBoxInAbsolutePosition(writer,
							box.getText(), box);
				} else if (box.getRotation() > 0f) {
					makeRotatedTextBoxtInAbsolutePosition(writer,
							box.getText(), box);
				} else {
					makeTexBoxtInAbsolutePosition(writer, box.getText(), box);
				}
			}
		}

		if (outputs != null) {
			for (int i = 0; i < outputs.size(); i++) {
				BoxConfig box = outputs.get(i);

				if (box.isVertical()) {
					makeVerticalTextBoxInAbsolutePosition(writer,
							getCarnetTextByMap(carnet, box.getMap()), box);
				} else if (box.getRotation() > 0f) {
					makeRotatedTextBoxtInAbsolutePosition(writer,
							getCarnetTextByMap(carnet, box.getMap()), box);
				} else {
					makeTexBoxtInAbsolutePosition(writer,
							getCarnetTextByMap(carnet, box.getMap()), box);
				}
			}
		}

		if (tables != null) {
			for (int i = 0; i < tables.size(); i++) {
				TableConfig tablecon = tables.get(i);
				makeTableInAbsolutePosition(writer, carnet, tablecon);
			}
		}

		if (images != null) {
			for (ImageBox image : images) {
				makeImageBoxtInAbsolutePosition(writer, image);
			}
		}

		drawStamp(writer, stamps);
	}

	protected String getCarnetTextByMap(CarnetATA carnet, String map) {
		return "";
	}

	public void makeBorderedTexBoxtInAbsolutePosition(PdfWriter writer,
			String text, BoxConfig config) throws IOException,
			DocumentException {

		PdfContentByte canvas = writer.getDirectContent();
		canvas.saveState();
		canvas.beginText();
		canvas.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
		canvas.setLineWidth(config.getWidthTextLine());
		Rectangle rect = new Rectangle(Utilities.millimetersToPoints(config
				.getXl()), Utilities.millimetersToPoints(config.getYl()),
				Utilities.millimetersToPoints(config.getXr()),
				Utilities.millimetersToPoints(config.getYr()));
		Phrase ptext = new Phrase(text, new Font(config.getBf(),
				config.getFontSize()));

		ColumnText column = new ColumnText(canvas);
		column.setSimpleColumn(ptext, rect.getLeft(), rect.getBottom(),
				rect.getRight(), rect.getTop(), config.getLeading(),
				config.getgAlign());
		column.go();
		canvas.endText();
		canvas.restoreState();

		PdfContentByte img = writer.getDirectContentUnder();
		img.saveState();
		rect.setBorderWidth(config.getWidthBorderLine());
		rect.setBorder(config.getBorder());
		img.rectangle(rect);
		img.stroke();
	}

	public void makeTexBoxtInAbsolutePosition(PdfWriter writer, String text,
			BoxConfig config) throws IOException, DocumentException {
		System.out.println("makeTexBoxtInAbsolutePosition");
		PdfContentByte canvas = writer.getDirectContent();
		canvas.saveState();
		canvas.beginText();
		canvas.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
		canvas.setLineWidth(config.getWidthTextLine());
		Rectangle rect = new Rectangle(Utilities.millimetersToPoints(config
				.getXl()), Utilities.millimetersToPoints(config.getYl()),
				Utilities.millimetersToPoints(config.getXr()),
				Utilities.millimetersToPoints(config.getYr()));

		Phrase ptext = new Phrase(text, new Font(config.getBf(),
				config.getFontSize()));

		ColumnText column = new ColumnText(canvas);
		column.setSimpleColumn(ptext, rect.getLeft(), rect.getBottom(),
				rect.getRight(), rect.getTop(), config.getLeading(),
				config.getgAlign());
		column.go();
		canvas.endText();
		canvas.restoreState();
	}

	public void makeBorderedBoxtInAbsolutePosition(PdfWriter writer,
			BoxConfig config) throws IOException, DocumentException {
		System.out.println("makeBorderedBoxtInAbsolutePosition");
		Rectangle rect = new Rectangle(Utilities.millimetersToPoints(config
				.getXl()), Utilities.millimetersToPoints(config.getYl()),
				Utilities.millimetersToPoints(config.getXr()),
				Utilities.millimetersToPoints(config.getYr()));
		PdfContentByte img = writer.getDirectContentUnder();
		img.saveState();
		rect.setBorderWidth(config.getWidthBorderLine());
		rect.setBorder(config.getBorder());
		img.rectangle(rect);
		img.stroke();
		img.restoreState();
	}

	public void makeFilledBorderedBoxtInAbsolutePosition(PdfWriter writer,
			BoxConfig config) throws IOException, DocumentException {
		System.out.println("makeFilledBorderedBoxtInAbsolutePosition");
		PdfContentByte canvas = writer.getDirectContentUnder();
		canvas.saveState();
		Rectangle rect = new Rectangle(Utilities.millimetersToPoints(config
				.getXl()), Utilities.millimetersToPoints(config.getYl()),
				Utilities.millimetersToPoints(config.getXr()),
				Utilities.millimetersToPoints(config.getYr()));
		rect.setBorderWidth(config.getWidthBorderLine());
		rect.setBorder(config.getBorder());
		rect.setGrayFill(config.getColorFill());
		canvas.rectangle(rect);
		canvas.fillStroke();
		canvas.restoreState();
	}

	public void makeImageBoxtInAbsolutePosition(PdfWriter writer,
			ImageBox imagebx) throws IOException, DocumentException {

		PdfContentByte canvas = writer.getDirectContentUnder();
		canvas.saveState();
		try {
			Image img = Image.getInstance(imagebx.getFilename());
			img.scaleAbsolute(
					Utilities.millimetersToPoints(imagebx.getXr()
							- imagebx.getXl()),
					Utilities.millimetersToPoints(imagebx.getYr()
							- imagebx.getYl()));
			img.setAbsolutePosition(
					Utilities.millimetersToPoints(imagebx.getXl()),
					Utilities.millimetersToPoints(imagebx.getYl()));
			writer.getDirectContent().addImage(img);
		} catch (Exception ex) {
			Logger.getLogger(CarnetApplication.class).error(
					"Image Box Error: ", ex);
		}
		canvas.restoreState();

	}

	public void makeRotatedTextBoxtInAbsolutePosition(PdfWriter writer,
			String text, BoxConfig config) throws IOException,
			DocumentException {
		System.out.println("makeRotatedTextBoxtInAbsolutePosition");
		PdfContentByte canvas = writer.getDirectContent();
		canvas.saveState();
		canvas.beginText();
		canvas.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
		canvas.setLineWidth(config.getWidthTextLine());

		Phrase ptext = new Phrase(text, new Font(config.getBf(),
				config.getFontSize()));
		ColumnText column = new ColumnText(canvas);
		ColumnText.showTextAligned(canvas, config.getgAlign(), ptext,
				Utilities.millimetersToPoints(config.getXl()),
				Utilities.millimetersToPoints(config.getYl()),
				config.getRotation());
		column.go();
		canvas.endText();
		canvas.restoreState();
	}

	public void makeVerticalTextBoxInAbsolutePosition(PdfWriter writer,
			String text, BoxConfig config) throws IOException,
			DocumentException {

		System.out.println("makeVerticalTextBoxInAbsolutePosition");
		PdfContentByte canvas = writer.getDirectContent();
		canvas.setLineWidth(config.getWidthTextLine());
		canvas.saveState(); // ?????
		VerticalText vbox = new VerticalText(canvas);
		vbox.setVerticalLayout(
				Utilities.millimetersToPoints(config.getXl()),
				Utilities.millimetersToPoints(config.getYr()),
				Utilities.millimetersToPoints(config.getYr())
						- Utilities.millimetersToPoints(config.getYl()), 1, 0);

		Phrase ptext = new Phrase(text, new Font(config.getBf(),
				config.getFontSize()));
		vbox.setAlignment(config.getgAlign());
		vbox.addText(ptext);
		vbox.go();
		canvas.restoreState(); // ?????

	}

	public void drawStamp(PdfWriter writer, List<Stamp> stamps) {
		if (stamps != null) {
			PdfContentByte canvas = writer.getDirectContentUnder();
			canvas.saveState();

			for (Stamp stamp : stamps) {

				canvas.setLineWidth(2);
				canvas.setLineDash(1, 1, 0);
				canvas.circle(Utilities.millimetersToPoints(stamp.getX()),
						Utilities.millimetersToPoints(stamp.getY()),
						Utilities.millimetersToPoints(stamp.getR()));
				canvas.stroke();

			}
			canvas.restoreState();
		}
	}

	protected PdfPTable makeTableInAbsolutePosition(PdfWriter writer,
			CarnetATA carnet, TableConfig tablecon) throws DocumentException,
			IOException {

		PdfPTable table = new PdfPTable(tablecon.getColnumber());
		makeTableHeader(writer, table, tablecon, carnet);
		makeTableBody(table, tablecon, carnet);
		makeTableFooter(writer, table, tablecon, carnet);
		table.completeRow();

		table.writeSelectedRows(0, -1,
				Utilities.millimetersToPoints(tablecon.getXl()),
				Utilities.millimetersToPoints(tablecon.getYr()),
				writer.getDirectContent());

		// System.out.println("Table height : " + table.getTotalHeight()
		// + "  Calculate height : " + getTableHeight(table));
		// System.out.println("Table height : "
		// + Utilities.pointsToMillimeters(table.getTotalHeight())
		// + "  Calculate height : "
		// + Utilities.pointsToMillimeters(getTableHeight(table)));
		return table;
	}

	private void makeTableFooter(PdfWriter writer, PdfPTable table,
			TableConfig tablecon, CarnetATA carnet) throws DocumentException,
			IOException {
		List<CTCell> row = tablecon.getFooterRow();
		row.get(1).setText("" + carnet.getTotal().getCount());
		row.get(2).setText("" + carnet.getTotal().getWeight());
		double floating = (carnet.getTotal().getValue() - (int)carnet.getTotal().getValue());
		
		row.get(3).setText(
				"" + carnet.getTotal().getValue() + CHAR_PARAGRAPH
				   + Numerals.english((int) carnet.getTotal().getValue()) 
				   + (floating > 0.0d ? floating : ""));

		writeRow(table, row);
		table.getRow(table.getLastCompletedRowIndex()).setMaxHeights(
				Utilities.millimetersToPoints(tablecon.getHeightrows().get(
						tablecon.getHeightrows().size() - 1)));
	}

	private void makeTableHeader(PdfWriter writer, PdfPTable table,
			TableConfig tablecon, CarnetATA carnet) throws DocumentException,
			IOException {

		float[] widths = new float[tablecon.getColnumber()];
		for (int i = 0; i < tablecon.getColnumber(); i++) {
			widths[i] = Utilities.millimetersToPoints(tablecon.getColwidths()
					.get(i));
		}
		table.setTotalWidth(Utilities.millimetersToPoints(tablecon.getWidth()));

		try {
			table.setWidths(widths);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		table.setLockedWidth(true);

		for (int i = 0; i < tablecon.getHeader().size(); i++) {
			List<CTCell> row = tablecon.getHeader().get(i);

			if (row.get(0).getText().indexOf("TOTAL or CARRIED") != -1) {
				row.get(1).setText("" + carnet.getTotal().getCount());
				row.get(2).setText("" + carnet.getTotal().getWeight());
				row.get(3).setText("" + carnet.getTotal().getValue());
			}
			writeRow(table, row);
			table.getRow(table.getLastCompletedRowIndex()).setMaxHeights(
					Utilities.millimetersToPoints(tablecon.getHeightrows().get(
							i)));
		}

	}

	private void writeRow(PdfPTable table, List<CTCell> row)
			throws DocumentException, IOException {
		for (int i = 0; i < row.size(); i++) {
			table.addCell(makeCell(row.get(i)));
		}
	}

	private PdfPCell makeCell(CTCell ctCell) throws DocumentException,
			IOException {
		BaseFont bf = BaseFont.createFont(System.getenv(WINDIR) + "\\fonts\\"
				+ ctCell.getFont() + ".TTF", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);
		PdfPCell cell = new PdfPCell(new Phrase(ctCell.getText(), new Font(bf,
				ctCell.getFontsize())));
		cell.setBorder(ctCell.getBorder());
		cell.setBorderWidth(ctCell.getBorderWidth());
		if (ctCell.isFill()) {
			cell.setGrayFill(0.9f);
		}
		cell.setHorizontalAlignment(ctCell.getAlign());
		cell.setVerticalAlignment(ctCell.getVerticalAlign());
		cell.setColspan(ctCell.getColspan());
		return cell;
	}

	private void makeTableBody(PdfPTable table, TableConfig tablecon,
			CarnetATA carnet) throws IOException, DocumentException {

		List<CTCell> row = tablecon.getBodyRow();
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		float ht = 0f;

		CarnetIterator cursor = carnet.getIterator();
		CarnetTotal total = carnet.getTotal();

		try {
			while (cursor.hasNext()) {
				ProductItem product = cursor.next();
				if (!product.getFirstNumber().isEmpty()) {
					row.get(0).setText(product.getFirstNumber());
					row.get(1).setText(product.getDescription());
					row.get(2).setText("" + product.getCount());
					row.get(3).setText("" + product.getWeight());
					row.get(4).setText("" + product.getValue());
					row.get(5).setText(product.getCountry().getCode());
					row.get(6).setText("");
					writeRow(table, row);

					ht += table.getRows().get(table.getLastCompletedRowIndex())
							.getMaxHeights();

					if (ht > Utilities.millimetersToPoints(tablecon
							.getWorkHeight())) {
						ht -= table.getRows()
								.get(table.getLastCompletedRowIndex())
								.getMaxHeights();
						table.deleteLastRow();
						cursor.prev();
						break;
					}

					total.setCount(total.getCount() + product.getCount());
					total.setValue(total.getValue() + product.getValue());
					total.setWeight(total.getWeight() + product.getWeight());

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		float delta = Utilities.millimetersToPoints(tablecon.getWorkHeight())
				- ht;
		PdfPRow tabrow = table.getRows().get(table.getLastCompletedRowIndex());
		tabrow.setMaxHeights(tabrow.getMaxHeights() + delta);

	}

	private float getTableHeight(PdfPTable table) {
		Float height = 0f;
		for (PdfPRow row : table.getRows()) {
			height += row.getMaxHeights();
		}
		return height;
	}
}
