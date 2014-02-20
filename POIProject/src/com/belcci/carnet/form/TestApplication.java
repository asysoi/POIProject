package com.belcci.carnet.form;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;

public class TestApplication {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestApplication window = new TestApplication();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(composite, SWT.BORDER);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Item A");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(2, false));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		lblNewLabel.setText("New Label");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Item B");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("Item C");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_3);

	}
}
