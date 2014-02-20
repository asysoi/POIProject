package com.belcci.carnet.form;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class CountryForm extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CountryForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell.setSize(436, 152);
		shell.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		
		Label lblCnhfyf = new Label(shell, SWT.NONE);
		lblCnhfyf.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lblCnhfyf.setAlignment(SWT.RIGHT);
		lblCnhfyf.setBounds(0, 38, 55, 15);
		lblCnhfyf.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(0, 66, 55, 15);
		label.setText("\u041A\u043E\u0434");
		
		Button button = new Button(shell, SWT.CHECK);
		button.setText("English");
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setBounds(348, 10, 64, 16);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.setBounds(256, 89, 75, 25);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
		button_2.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_2.setBounds(337, 89, 75, 25);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(64, 35, 351, 21);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(64, 63, 76, 21);

	}

}
