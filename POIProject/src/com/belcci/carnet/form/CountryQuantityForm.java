package com.belcci.carnet.form;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.CountryQuantity;

public class CountryQuantityForm extends Dialog {
    
	protected Object result;
	protected Shell shell;
	private Text txtQuantity;
	private CountryQuantity qcountry;
	private String title; 
	private CCombo cbCountry;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CountryQuantityForm(Shell parent, int style) {
		super(parent, style);
		setText(title != null ? title : "");
	}

	public CountryQuantity getQcountry() {
		return qcountry;
	}

	public void setQcountry(CountryQuantity qcountry) {
		this.qcountry = qcountry;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		shell = new Shell(getParent(), getStyle());
		shell.setSize(480, 156);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(120, 21, 78, 20);
		label.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		
		cbCountry = new CCombo(shell, SWT.BORDER);
		cbCountry.setBounds(204, 21, 248, 21);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lblNewLabel.setBounds(11, 54, 186, 20);
		lblNewLabel.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u0432\u044A\u0435\u0437\u0434\u043E\u0432/\u0432\u044B\u0435\u0437\u0434\u043E\u0432");
		
		txtQuantity = new Text(shell, SWT.BORDER);
		txtQuantity.addVerifyListener(new DigitalListener());
		txtQuantity.setBounds(205, 53, 76, 21);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (qcountry == null) {
					qcountry = new CountryQuantity();
				}
				qcountry.setCountry(CountryList.getInstance().findCountryByName(cbCountry.getText()));
				qcountry.setQuantity(Integer.parseInt(txtQuantity.getText().isEmpty() ? "0" : txtQuantity.getText()));
				result = qcountry;
				shell.close();
			}
		});
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setBounds(296, 93, 75, 25);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		button_1.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.setBounds(377, 93, 75, 25);
		fillInCountryList();
		fillInQCountry();
	}

	private void fillInCountryList() {
		cbCountry.removeAll();
		List<Country> list = CountryList.getInstance();
		
		for (Country country: list) {
			cbCountry.add(country.getName());
		}
	}

	private void fillInQCountry() {
		if (qcountry != null) { 
			cbCountry.select(selectCountryInList(qcountry.getCountry()));
			txtQuantity.setText("" + qcountry.getQuantity());
		}
	}

	private int selectCountryInList(Country country) {
		int sel = 0;

		for (String item : cbCountry.getItems()) {
			if (country.getName().equals(item)) {
				break;
			}
			sel++;
		}
		return sel;
	}

	
	
}
