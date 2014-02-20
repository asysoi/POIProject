package com.belcci.carnet.form;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.belcci.carnet.model.Company;
import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.ProductItem;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Combo;

public class ProductItemForm extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtNumber;
	private Text txtDescription;
	private Text txtAmmount;
	private ProductItem product;
	private Text txtWeight;
	private Text txtValue;
	private Combo cbCountry;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ProductItemForm(Shell parent, int style) {
		super(parent, style);
		// this.product = product;
	}

	/**
	 * Open the dialog.
	 * 
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
		shell.setSize(522, 398);
		shell.setText("\u0422\u043E\u0432\u0430\u0440\u043D\u0430\u044F \u043F\u043E\u0437\u0438\u0446\u0438\u044F");
		shell.setLayout(null);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel
				.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lblNewLabel.setBounds(36, 22, 116, 15);
		lblNewLabel
				.setText("\u041F\u043E\u0440\u044F\u0434\u043A\u043E\u0432\u044B\u0439 \u043D\u043E\u043C\u0435\u0440");

		Label label = new Label(shell, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		label.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label.setBounds(36, 49, 114, 15);
		label.setText("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435 \u0442\u043E\u0432\u0430\u0440\u0430");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_1.setBounds(60, 204, 95, 15);
		label_1.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E");

		txtNumber = new Text(shell, SWT.BORDER);
		txtNumber.addVerifyListener(new DigitalListener());

		txtNumber.setBounds(161, 22, 333, 21);

		txtDescription = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtDescription.setBounds(161, 49, 333, 144);

		txtAmmount = new Text(shell, SWT.BORDER);
		txtAmmount.addVerifyListener(new DigitalListener());
		txtAmmount.setBounds(161, 199, 131, 21);

		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				saveProductContent();
				shell.close();
			}
		});
		button.setBounds(340, 335, 75, 25);
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		button_1.setBounds(419, 335, 75, 25);
		button_1.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_2.setBounds(60, 234, 95, 15);
		label_2.setText("\u0412\u0435\u0441/\u041E\u0431\u044A\u0435\u043C");

		txtWeight = new Text(shell, SWT.BORDER);
		txtWeight.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent event) {
				verifyDouble(event);
			}
		});

		txtWeight.setBounds(161, 230, 132, 21);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_3.setBounds(61, 262, 95, 15);
		label_3.setText("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C");

		txtValue = new Text(shell, SWT.BORDER);
		txtValue.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent event) {
				verifyDouble(event);
			}
		});

		txtValue.setBounds(162, 260, 131, 21);

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setAlignment(SWT.RIGHT);
		label_4.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_4.setBounds(10, 292, 146, 15);
		label_4.setText("\u0421\u0442\u0440\u0430\u043D\u0430 \u043F\u0440\u043E\u0438\u0441\u0445\u043E\u0436\u0434\u0435\u043D\u0438\u044F");
		
		cbCountry = new Combo(shell, SWT.NONE);
		cbCountry.setBounds(162, 290, 332, 23);
        fillInCountryList(cbCountry);
		loadProductContent();
	}

	private void loadProductContent() {
		txtNumber.setText(product.getFirstNumber());
		txtDescription.setText(product.getDescription());
		txtAmmount.setText("" + product.getCount());
		txtWeight.setText("" + product.getWeight());
		txtValue.setText("" + product.getValue());
	}

	private void saveProductContent() {
		product.setFirstNumber(txtNumber.getText());
		product.setDescription(txtDescription.getText());
		product.setCount(Integer.parseInt(txtAmmount.getText()));
		product.setWeight(Double.parseDouble(txtWeight.getText()));
		product.setValue(Double.parseDouble(txtValue.getText()));
		product.setCountry(CountryList.getInstance().findCountryByName(cbCountry.getText()));
	}

	public ProductItem getProduct() {
		return product;
	}

	public void setProduct(ProductItem product) {
		this.product = product;
	}

	private void verifyDouble(VerifyEvent event) {
		switch (event.keyCode) {
		case SWT.BS: // Backspace
		case SWT.DEL: // Delete
		case SWT.HOME: // Home
		case SWT.END: // End
		case SWT.ARROW_LEFT: // Left arrow
		case SWT.ARROW_RIGHT: // Right arrow
		return;
		}

		final String oldS = ((Text) event.getSource()).getText();
		String newS = oldS.substring(0, event.start) + event.text
				+ oldS.substring(event.end);

		boolean isDouble = true;
		try {
			Double.parseDouble(newS);
		} catch (NumberFormatException ex) {
			isDouble = false;
		}

		if (!isDouble) {
			event.doit = false;
		}
	}
	
	private void fillInCountryList(Combo cbc) {
		String name = null;
		String ename = null;
		int sel = -1;
	    CountryList countries = CountryList.getInstance();
	    Country country = product.getCountry();
	    
	    if (country != null) {
	    	name = country.getName();
	    	ename = country.getEname();
	    } 
	      
	    for (Country ctry: countries) {
		    cbc.add(ctry.getName());
		    if ((ename != null && ename.equals(ctry.getEname())) || (name != null && name.equals(ctry.getName()))) {
		    	System.out.println(ename + " | " + name + " | "  + ctry.getEname() + " | " + ctry.getName());
		    	sel = cbc.getItemCount() - 1;
		    }
	    }
	    cbc.select(sel);
	    name = null;
	    ename = null;
	    country = null;
	}
}
