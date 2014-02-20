package com.belcci.carnet.form;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class JobForm extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public JobForm(Shell parent, int style) {
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
		shell.setSize(450, 138);
		shell.setText("\u0414\u043E\u043B\u0436\u043D\u043E\u0441\u0442\u044C");
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setBounds(267, 73, 75, 25);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.setBounds(348, 73, 75, 25);
		
		Button button_2 = new Button(shell, SWT.CHECK);
		button_2.setText("English");
		button_2.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_2.setBounds(358, 10, 64, 16);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(104, 35, 321, 21);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435");
		label.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(10, 38, 88, 15);

	}

}
