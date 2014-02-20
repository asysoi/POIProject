package com.belcci.carnet.form;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.belcci.carnet.model.Party;

public class PartyForm extends Dialog {

	private Object result;
	private Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text txtName;
	private Text text_9;
	private Text text_11;
	private Text text_12;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	private Text text_22;
	private Text text_10;
	private Text text_23;
	private Text text_24;
	private Text text_25;
	private Text text_26;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_27;
	private Party company;
	private boolean isSaved = false;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PartyForm(Shell parent, int style) {
		super(parent, style);
		setText("Организация");
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
		shell.setSize(600, 393);
		shell.setText("\u041E\u0440\u0433\u0430\u043D\u0438\u0437\u0430\u0446\u0438\u044F");
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnCheckButton.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnCheckButton.setBounds(518, 10, 64, 16);
		btnCheckButton.setText("English");
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		label_8.setBounds(10, 31, 103, 17);
		label_8.setText("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435");
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		txtName.setBounds(119, 30, 464, 21);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setSaved(true); 
			}
		});
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button.setBounds(426, 330, 75, 25);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
		button_1.setBounds(507, 330, 75, 25);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		tabFolder.setBounds(9, 63, 574, 261);
		
		TabItem tabContact = new TabItem(tabFolder, SWT.NONE);
		tabContact.setText("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabContact.setControl(composite);
		
		Label label_1 = new Label(composite, SWT.RIGHT);
		label_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_1.setBounds(64, 77, 33, 15);
		label_1.setText("\u0423\u041D\u041F");
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text.setBounds(103, 75, 206, 21);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(337, 16, 33, 15);
		label_5.setText("\u0422\u0435\u043B");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_5.setBounds(376, 14, 180, 21);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_7.setBounds(329, 43, 41, 15);
		label_7.setAlignment(SWT.RIGHT);
		label_7.setText("\u0424\u0430\u043A\u0441");
		
		text_7 = new Text(composite, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_7.setBounds(376, 45, 180, 21);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(337, 77, 33, 15);
		label_6.setText("Email");
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_6.setBounds(376, 75, 180, 21);
		
		Label label_9 = new Label(composite, SWT.RIGHT);
		label_9.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_9.setBounds(10, 15, 86, 15);
		label_9.setAlignment(SWT.RIGHT);
		label_9.setText("\u0420\u0443\u043A\u043E\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C");
		
		text_9 = new Text(composite, SWT.BORDER);
		text_9.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_9.setBounds(102, 14, 207, 21);
		
		Label label_10 = new Label(composite, SWT.RIGHT);
		label_10.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_10.setBounds(30, 50, 67, 15);
		label_10.setText("\u0414\u043E\u043B\u0436\u043D\u043E\u0441\u0442\u044C");
		
		Combo combo_2 = new Combo(composite, SWT.NONE);
		combo_2.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		combo_2.setBounds(103, 44, 206, 23);
		
		Group group_1 = new Group(composite, SWT.SHADOW_NONE);
		group_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		group_1.setText("\u041F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u044C \u043A\u0430\u0440\u043D\u0435\u0442\u0430 \u043D\u0430 \u043F\u0440\u0435\u0434\u043F\u0440\u0438\u044F\u0442\u0438\u0438");
		group_1.setBounds(10, 109, 546, 54);
		
		text_11 = new Text(group_1, SWT.BORDER);
		text_11.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_11.setBounds(67, 21, 193, 21);
		
		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setAlignment(SWT.RIGHT);
		label_12.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_12.setBounds(266, 24, 104, 15);
		label_12.setText("N \u0434\u043E\u0432\u0435\u0440\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		
		text_12 = new Text(group_1, SWT.BORDER);
		text_12.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_12.setBounds(376, 21, 160, 21);
		
		text_25 = new Text(group_1, SWT.RIGHT);
		text_25.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_25.setText("\u0424\u0418\u041E");
		text_25.setBounds(10, 25, 49, 15);
		
		Group group = new Group(composite, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		group.setText("\u041F\u043E\u043B\u0443\u0447\u0430\u0442\u0435\u043B\u044C \u043A\u0430\u0440\u043D\u0435\u0442\u0430, \u0435\u0441\u043B\u0438 \u043E\u0442\u043B\u0438\u0447\u0435\u043D \u043E\u0442 \u043F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u044F");
		group.setBounds(10, 169, 546, 54);
		
		text_10 = new Text(group, SWT.BORDER);
		text_10.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_10.setBounds(68, 23, 194, 21);
		
		Label label_26 = new Label(group, SWT.RIGHT);
		label_26.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_26.setText("N \u0434\u043E\u0432\u0435\u0440\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		label_26.setBounds(268, 26, 102, 18);
		
		text_23 = new Text(group, SWT.BORDER);
		text_23.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_23.setBounds(376, 23, 160, 21);
		
		text_24 = new Text(group, SWT.RIGHT);
		text_24.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_24.setText("\u0424\u0418\u041E");
		text_24.setBounds(10, 26, 49, 15);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u0410\u0434\u0440\u0435\u0441\u0430");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_1);
		
		Group group_2 = new Group(composite_1, SWT.NONE);
		group_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		group_2.setText("\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u0430\u0434\u0440\u0435\u0441");
		group_2.setBounds(10, 10, 267, 211);
		
		Combo combo = new Combo(group_2, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		combo.setBounds(64, 22, 191, 23);
		
		Label label = new Label(group_2, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label.setBounds(10, 25, 48, 15);
		label.setAlignment(SWT.RIGHT);
		label.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		
		Label label_11 = new Label(group_2, SWT.NONE);
		label_11.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_11.setBounds(10, 58, 45, 15);
		label_11.setAlignment(SWT.RIGHT);
		label_11.setText("\u0413\u043E\u0440\u043E\u0434");
		
		text_26 = new Text(group_2, SWT.BORDER);
		text_26.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_26.setBounds(64, 57, 191, 21);
		
		Label label_13 = new Label(group_2, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_13.setBounds(10, 91, 48, 15);
		label_13.setAlignment(SWT.RIGHT);
		label_13.setText("\u0418\u043D\u0434\u0435\u043A\u0441");
		
		text_1 = new Text(group_2, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_1.setBounds(64, 88, 139, 21);
		
		Label label_2 = new Label(group_2, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_2.setBounds(10, 121, 48, 15);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u0423\u043B\u0438\u0446\u0430");
		
		text_2 = new Text(group_2, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_2.setBounds(64, 118, 191, 21);
		
		Label label_3 = new Label(group_2, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_3.setBounds(10, 152, 48, 15);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u0414\u043E\u043C");
		
		text_3 = new Text(group_2, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_3.setBounds(64, 149, 76, 21);
		
		Label label_4 = new Label(group_2, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_4.setBounds(18, 183, 40, 15);
		label_4.setAlignment(SWT.RIGHT);
		label_4.setText("\u041E\u0444\u0438\u0441");
		
		text_4 = new Text(group_2, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_4.setBounds(64, 180, 76, 21);
		
		Group group_3 = new Group(composite_1, SWT.NONE);
		group_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		group_3.setText("\u041F\u043E\u0447\u0442\u043E\u0432\u044B\u0439 \u0430\u0434\u0440\u0435\u0441");
		group_3.setBounds(289, 10, 267, 211);
		
		Combo combo_1 = new Combo(group_3, SWT.NONE);
		combo_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		combo_1.setBounds(64, 22, 191, 23);
		
		Label label_14 = new Label(group_3, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_14.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		label_14.setAlignment(SWT.RIGHT);
		label_14.setBounds(10, 25, 48, 15);
		
		Label label_15 = new Label(group_3, SWT.NONE);
		label_15.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_15.setText("\u0413\u043E\u0440\u043E\u0434");
		label_15.setAlignment(SWT.RIGHT);
		label_15.setBounds(10, 58, 45, 15);
		
		text_13 = new Text(group_3, SWT.BORDER);
		text_13.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_13.setBounds(64, 57, 191, 21);
		
		Label label_16 = new Label(group_3, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_16.setText("\u0418\u043D\u0434\u0435\u043A\u0441");
		label_16.setAlignment(SWT.RIGHT);
		label_16.setBounds(10, 91, 48, 15);
		
		text_14 = new Text(group_3, SWT.BORDER);
		text_14.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_14.setBounds(64, 88, 139, 21);
		
		Label label_17 = new Label(group_3, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_17.setText("\u0423\u043B\u0438\u0446\u0430");
		label_17.setAlignment(SWT.RIGHT);
		label_17.setBounds(10, 121, 48, 15);
		
		text_15 = new Text(group_3, SWT.BORDER);
		text_15.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_15.setBounds(64, 118, 191, 21);
		
		Label label_18 = new Label(group_3, SWT.NONE);
		label_18.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_18.setText("\u0414\u043E\u043C");
		label_18.setAlignment(SWT.RIGHT);
		label_18.setBounds(10, 152, 48, 15);
		
		text_16 = new Text(group_3, SWT.BORDER);
		text_16.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_16.setBounds(64, 149, 76, 21);
		
		Label label_25 = new Label(group_3, SWT.NONE);
		label_25.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_25.setText("\u041E\u0444\u0438\u0441");
		label_25.setAlignment(SWT.RIGHT);
		label_25.setBounds(18, 183, 40, 15);
		
		text_27 = new Text(group_3, SWT.BORDER);
		text_27.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_27.setBounds(64, 180, 76, 21);
		
		TabItem tabBank = new TabItem(tabFolder, SWT.NONE);
		tabBank.setText("\u0420\u0435\u043A\u0432\u0438\u0437\u0438\u0442\u044B \u0431\u0430\u043D\u043A\u0430");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tabBank.setControl(composite_3);
		
		Label label_19 = new Label(composite_3, SWT.NONE);
		label_19.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_19.setAlignment(SWT.RIGHT);
		label_19.setBounds(10, 33, 140, 15);
		label_19.setText("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0411\u0430\u043D\u043A\u0430");
		
		text_17 = new Text(composite_3, SWT.BORDER);
		text_17.setBounds(160, 27, 387, 21);
		
		Label label_20 = new Label(composite_3, SWT.NONE);
		label_20.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_20.setAlignment(SWT.RIGHT);
		label_20.setBounds(68, 65, 82, 15);
		label_20.setText("\u041A\u043E\u0434 \u0411\u0430\u043D\u043A\u0430");
		
		text_18 = new Text(composite_3, SWT.BORDER);
		text_18.setBounds(161, 62, 71, 21);
		
		Label label_21 = new Label(composite_3, SWT.RIGHT);
		label_21.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_21.setBounds(10, 104, 140, 15);
		label_21.setText("\u0420\u0430\u0441\u0447\u0435\u0442\u043D\u044B\u0439 \u0441\u0447\u0435\u0442");
		
		text_19 = new Text(composite_3, SWT.BORDER);
		text_19.setBounds(160, 101, 233, 21);
		
		Label label_22 = new Label(composite_3, SWT.NONE);
		label_22.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_22.setText("\u0423\u043B\u0438\u0446\u0430");
		label_22.setAlignment(SWT.RIGHT);
		label_22.setBounds(80, 136, 70, 15);
		
		text_20 = new Text(composite_3, SWT.BORDER);
		text_20.setBounds(160, 133, 387, 21);
		
		Label label_23 = new Label(composite_3, SWT.NONE);
		label_23.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_23.setText("\u0414\u043E\u043C");
		label_23.setAlignment(SWT.RIGHT);
		label_23.setBounds(80, 171, 70, 15);
		
		text_21 = new Text(composite_3, SWT.BORDER);
		text_21.setBounds(160, 166, 50, 21);
		
		Label label_24 = new Label(composite_3, SWT.NONE);
		label_24.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_24.setAlignment(SWT.RIGHT);
		label_24.setText("\u041E\u0444\u0438\u0441");
		label_24.setBounds(90, 203, 60, 15);
		
		text_22 = new Text(composite_3, SWT.BORDER);
		text_22.setBounds(160, 200, 50, 21);
		
		
        loadPartyContent();
	}
	
	private void loadPartyContent() {
	    if (company != null) {
	    	txtName.setText(company.getName());
	    }
	}

	public void setParty(Party company) {
		this.company = company;
	}
	
	public Party getParty() {
		return company;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	
	}
