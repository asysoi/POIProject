package com.belcci.carnet.form;

import java.util.Calendar;
import java.util.List;

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

import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.AddressType;
import com.belcci.carnet.model.Company;
import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.Employer;
import com.belcci.carnet.model.Job;
import com.belcci.carnet.model.JobList;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Passport;
import com.belcci.carnet.model.Person;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class PartyForm extends Dialog {

	private Object result;
	private Shell shell;
	private Text txtUNP;
	private Text txtIndex;
	private Text txtStreet;
	private Text txtHouse;
	private Text txtOffice;
	private Text txtPhone;
	private Text txtEmail;
	private Text txtFax;
	private Text txtName;
	private Text txtDirector;
	private Text txtRepresenter;
	private Text txtRepresenterTrust;
	private Text txtBankName;
	private Text txtBankCode;
	private Text txtBankAccount;
	private Text txtBankStreet;
	private Text txtBankHouse;
	private Text txtBankOffice;
	private Text txtReciever;
	private Text txtRecieverTrust;
	private Text text_24;
	private Text text_25;
	private Text txtCity;
	private Text txtMailCity;
	private Text txtMailIndex;
	private Text txtMailStreet;
	private Text txtMailHouse;
	private Text txtMailOffice;
	private Company company;
	private boolean isSaved = false;
	private Combo cboCountry;
	private Combo cboMailCountry;
	private Combo cboJob;
	private Button chkEnglish;
	private Address waddr;
	private Address maddr;
	private Text txtBankIndex;
	private Address baddr;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PartyForm(Shell parent, int style) {
		super(parent, style);
		setText("Организация");
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
		shell.setSize(600, 393);
		shell.setText("\u041E\u0440\u0433\u0430\u043D\u0438\u0437\u0430\u0446\u0438\u044F");

		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		label_8.setBounds(10, 31, 103, 17);
		label_8.setText("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435");

		txtName = new Text(shell, SWT.BORDER);
		txtName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					company.setEname(txtName.getText());
				} else {
					company.setName(txtName.getText());
				}
			}
		});
		txtName.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		txtName.setBounds(119, 30, 464, 21);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setSaved(true);
				shell.close();
			}
		});
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button.setBounds(426, 330, 75, 25);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setSaved(false);
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

		txtUNP = new Text(composite, SWT.BORDER);
		txtUNP.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				company.setUnp(txtUNP.getText());
			}
		});
		txtUNP.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtUNP.setBounds(103, 75, 206, 21);

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(344, 16, 26, 15);
		label_5.setText("\u0422\u0435\u043B");

		txtPhone = new Text(composite, SWT.BORDER);
		txtPhone.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtPhone.setBounds(376, 14, 180, 21);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_7.setBounds(329, 47, 41, 15);
		label_7.setAlignment(SWT.RIGHT);
		label_7.setText("\u0424\u0430\u043A\u0441");

		txtFax = new Text(composite, SWT.BORDER);
		txtFax.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtFax.setBounds(376, 45, 180, 21);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(337, 77, 33, 15);
		label_6.setText("Email");

		txtEmail = new Text(composite, SWT.BORDER);
		txtEmail.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtEmail.setBounds(376, 75, 180, 21);

		Label label_9 = new Label(composite, SWT.RIGHT);
		label_9.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_9.setBounds(10, 15, 86, 15);
		label_9.setAlignment(SWT.RIGHT);
		label_9.setText("\u0420\u0443\u043A\u043E\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C");

		txtDirector = new Text(composite, SWT.BORDER);
		txtDirector.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				cboJob.setEnabled(! txtDirector.getText().isEmpty());
			}
		});
		txtDirector.setEditable(false);
		txtDirector.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtDirector.setBounds(102, 14, 207, 21);

		Label label_10 = new Label(composite, SWT.RIGHT);
		label_10.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_10.setBounds(30, 50, 67, 15);
		label_10.setText("\u0414\u043E\u043B\u0436\u043D\u043E\u0441\u0442\u044C");

		cboJob = new Combo(composite, SWT.NONE);
		cboJob.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				if (company.getDirector() != null
						&& company.getDirector().getPerson() != null) {
					company.getDirector().setPosition(
							JobList.getInstance().get(
									cboJob.getSelectionIndex()));
				}
			}
		});

		cboJob.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		cboJob.setBounds(103, 44, 206, 23);

		Group group_1 = new Group(composite, SWT.SHADOW_NONE);
		group_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		group_1.setText("\u041F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u044C \u043A\u0430\u0440\u043D\u0435\u0442\u0430 \u043D\u0430 \u043F\u0440\u0435\u0434\u043F\u0440\u0438\u044F\u0442\u0438\u0438");
		group_1.setBounds(10, 109, 546, 54);

		txtRepresenter = new Text(group_1, SWT.BORDER);
		txtRepresenter.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				txtRepresenterTrust.setEditable(! txtRepresenter.getText().isEmpty());
			}
		});
		txtRepresenter.setEditable(false);
		txtRepresenter.setFont(SWTResourceManager.getFont("Arial", 9,
				SWT.NORMAL));
		txtRepresenter.setBounds(52, 21, 182, 21);

		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setAlignment(SWT.RIGHT);
		label_12.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_12.setBounds(266, 24, 104, 15);
		label_12.setText("N \u0434\u043E\u0432\u0435\u0440\u0435\u043D\u043D\u043E\u0441\u0442\u0438");

		txtRepresenterTrust = new Text(group_1, SWT.BORDER);
		txtRepresenterTrust.setEditable(false);
		txtRepresenterTrust.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
			}
		});
		txtRepresenterTrust.setFont(SWTResourceManager.getFont("Arial", 9,
				SWT.NORMAL));
		txtRepresenterTrust.setBounds(376, 21, 160, 21);

		text_25 = new Text(group_1, SWT.RIGHT);
		text_25.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_25.setText("\u0424\u0418\u041E");
		text_25.setBounds(10, 25, 33, 15);

		Button btnRepresenter = new Button(group_1, SWT.NONE);
		btnRepresenter.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				PartyListForm pform = new PartyListForm(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				pform.setPerson(true);
				pform.open();

				if (pform.isSelected()) {
					Party party = pform.getParty();
					Employer representer = new Employer();
					representer.setPerson((Person) party);
					company.setPresenter(representer);
					txtRepresenter.setText(representer.getPerson().getName());
				}
			}
		});
		btnRepresenter.setText("...");
		btnRepresenter.setBounds(234, 20, 33, 23);

		Group group = new Group(composite, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		group.setText("\u041F\u043E\u043B\u0443\u0447\u0430\u0442\u0435\u043B\u044C \u043A\u0430\u0440\u043D\u0435\u0442\u0430, \u0435\u0441\u043B\u0438 \u043E\u0442\u043B\u0438\u0447\u0435\u043D \u043E\u0442 \u043F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u044F");
		group.setBounds(10, 169, 546, 54);

		txtReciever = new Text(group, SWT.BORDER);
		txtReciever.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
			   txtRecieverTrust.setEditable(! txtReciever.getText().isEmpty());
			}
		});
		txtReciever.setEditable(false);
		txtReciever.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtReciever.setBounds(52, 23, 182, 21);

		Label label_26 = new Label(group, SWT.RIGHT);
		label_26.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_26.setText("N \u0434\u043E\u0432\u0435\u0440\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		label_26.setBounds(268, 26, 102, 18);

		txtRecieverTrust = new Text(group, SWT.BORDER);
		txtRecieverTrust.setEditable(false);
		txtRecieverTrust.setFont(SWTResourceManager.getFont("Arial", 9,
				SWT.NORMAL));
		txtRecieverTrust.setBounds(376, 23, 160, 21);

		text_24 = new Text(group, SWT.RIGHT);
		text_24.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		text_24.setText("\u0424\u0418\u041E");
		text_24.setBounds(10, 26, 33, 15);

		Button btnReciever = new Button(group, SWT.NONE);
		btnReciever.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				PartyListForm pform = new PartyListForm(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				pform.setPerson(true);
				pform.open();

				if (pform.isSelected()) {
					Party party = pform.getParty();
					Employer reciever = new Employer();
					reciever.setPerson((Person) party);
					company.setReciever(reciever);
					txtReciever.setText(reciever.getPerson().getName());
				}
				
			}
		});
		btnReciever.setText("...");
		btnReciever.setBounds(234, 22, 33, 23);

		Button btnDirector = new Button(composite, SWT.NONE);
		btnDirector.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				PartyListForm pform = new PartyListForm(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				pform.setPerson(true);
				pform.open();

				if (pform.isSelected()) {
					Party party = pform.getParty();
					Employer director = new Employer();
					director.setPerson((Person) party);
					company.setDirector(director);
					txtDirector.setText(director.getPerson().getName());
				}
			}
		});
		btnDirector.setBounds(309, 13, 33, 23);
		btnDirector.setText("...");

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u0410\u0434\u0440\u0435\u0441\u0430");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_1);

		Group group_2 = new Group(composite_1, SWT.NONE);
		group_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		group_2.setText("\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u0430\u0434\u0440\u0435\u0441");
		group_2.setBounds(10, 10, 267, 211);

		cboCountry = new Combo(group_2, SWT.NONE);
		cboCountry.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				getWorkAddress().setCountry(
						CountryList.getInstance().get(
								cboCountry.getSelectionIndex()));
			}
		});
		cboCountry.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		cboCountry.setBounds(64, 22, 191, 23);

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

		txtCity = new Text(group_2, SWT.BORDER);
		txtCity.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					getWorkAddress().setEcity(txtCity.getText());
				} else {
					getWorkAddress().setCity(txtCity.getText());
				}
			}
		});
		txtCity.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtCity.setBounds(64, 57, 191, 21);

		Label label_13 = new Label(group_2, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_13.setBounds(10, 91, 48, 15);
		label_13.setAlignment(SWT.RIGHT);
		label_13.setText("\u0418\u043D\u0434\u0435\u043A\u0441");

		txtIndex = new Text(group_2, SWT.BORDER);
		txtIndex.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getWorkAddress().setIndex(txtIndex.getText());
			}
		});
		txtIndex.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtIndex.setBounds(64, 88, 139, 21);

		Label label_2 = new Label(group_2, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_2.setBounds(10, 121, 48, 15);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("\u0423\u043B\u0438\u0446\u0430");

		txtStreet = new Text(group_2, SWT.BORDER);
		txtStreet.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					getWorkAddress().setEline(txtStreet.getText());
				} else {
					getWorkAddress().setLine(txtStreet.getText());
				}
			}
		});
		txtStreet.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtStreet.setBounds(64, 118, 191, 21);

		Label label_3 = new Label(group_2, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_3.setBounds(10, 152, 48, 15);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("\u0414\u043E\u043C");

		txtHouse = new Text(group_2, SWT.BORDER);
		txtHouse.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getWorkAddress().setHouse(txtHouse.getText());
			}
		});
		txtHouse.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtHouse.setBounds(64, 149, 76, 21);

		Label label_4 = new Label(group_2, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_4.setBounds(18, 183, 40, 15);
		label_4.setAlignment(SWT.RIGHT);
		label_4.setText("\u041E\u0444\u0438\u0441");

		txtOffice = new Text(group_2, SWT.BORDER);
		txtOffice.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getWorkAddress().setOffice(txtOffice.getText());
			}
		});
		txtOffice.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtOffice.setBounds(64, 180, 76, 21);

		Group group_3 = new Group(composite_1, SWT.NONE);
		group_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		group_3.setText("\u041F\u043E\u0447\u0442\u043E\u0432\u044B\u0439 \u0430\u0434\u0440\u0435\u0441");
		group_3.setBounds(289, 10, 267, 211);

		cboMailCountry = new Combo(group_3, SWT.NONE);
		cboMailCountry.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				getMailAddress().setCountry(
						CountryList.getInstance().get(
								cboMailCountry.getSelectionIndex()));
			}
		});
		cboMailCountry.setFont(SWTResourceManager.getFont("Arial", 9,
				SWT.NORMAL));
		cboMailCountry.setBounds(64, 22, 191, 23);

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

		txtMailCity = new Text(group_3, SWT.BORDER);
		txtMailCity.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					getMailAddress().setEcity(txtMailCity.getText());
				} else {
					getMailAddress().setCity(txtMailCity.getText());
				}
			}
		});
		txtMailCity.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtMailCity.setBounds(64, 57, 191, 21);

		Label label_16 = new Label(group_3, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_16.setText("\u0418\u043D\u0434\u0435\u043A\u0441");
		label_16.setAlignment(SWT.RIGHT);
		label_16.setBounds(10, 91, 48, 15);

		txtMailIndex = new Text(group_3, SWT.BORDER);
		txtMailIndex.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getMailAddress().setIndex(txtMailIndex.getText());
			}
		});
		txtMailIndex
				.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtMailIndex.setBounds(64, 88, 139, 21);

		Label label_17 = new Label(group_3, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_17.setText("\u0423\u043B\u0438\u0446\u0430");
		label_17.setAlignment(SWT.RIGHT);
		label_17.setBounds(10, 121, 48, 15);

		txtMailStreet = new Text(group_3, SWT.BORDER);
		txtMailStreet.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					getMailAddress().setEline(txtMailStreet.getText());
				} else {
					getMailAddress().setLine(txtMailStreet.getText());
				}

			}
		});
		txtMailStreet.setFont(SWTResourceManager
				.getFont("Arial", 9, SWT.NORMAL));
		txtMailStreet.setBounds(64, 118, 191, 21);

		Label label_18 = new Label(group_3, SWT.NONE);
		label_18.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_18.setText("\u0414\u043E\u043C");
		label_18.setAlignment(SWT.RIGHT);
		label_18.setBounds(10, 152, 48, 15);

		txtMailHouse = new Text(group_3, SWT.BORDER);
		txtMailHouse.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getMailAddress().setHouse(txtMailHouse.getText());
			}
		});
		txtMailHouse
				.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtMailHouse.setBounds(64, 149, 76, 21);

		Label label_25 = new Label(group_3, SWT.NONE);
		label_25.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_25.setText("\u041E\u0444\u0438\u0441");
		label_25.setAlignment(SWT.RIGHT);
		label_25.setBounds(18, 183, 40, 15);

		txtMailOffice = new Text(group_3, SWT.BORDER);
		txtMailOffice.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getMailAddress().setOffice(txtMailOffice.getText());
			}
		});
		txtMailOffice.setFont(SWTResourceManager
				.getFont("Arial", 9, SWT.NORMAL));
		txtMailOffice.setBounds(64, 180, 76, 21);

		TabItem tabBank = new TabItem(tabFolder, SWT.NONE);
		tabBank.setText("\u0420\u0435\u043A\u0432\u0438\u0437\u0438\u0442\u044B \u0431\u0430\u043D\u043A\u0430");

		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tabBank.setControl(composite_3);

		Label label_19 = new Label(composite_3, SWT.NONE);
		label_19.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_19.setAlignment(SWT.RIGHT);
		label_19.setBounds(10, 29, 140, 15);
		label_19.setText("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435 \u0411\u0430\u043D\u043A\u0430");

		txtBankName = new Text(composite_3, SWT.BORDER);
		txtBankName.setEditable(false);
		txtBankName.setBounds(160, 27, 331, 21);

		Label label_20 = new Label(composite_3, SWT.NONE);
		label_20.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_20.setAlignment(SWT.RIGHT);
		label_20.setBounds(68, 65, 82, 15);
		label_20.setText("\u041A\u043E\u0434 \u0411\u0430\u043D\u043A\u0430");

		txtBankCode = new Text(composite_3, SWT.BORDER);
		txtBankCode.setBounds(161, 62, 71, 21);

		Label label_21 = new Label(composite_3, SWT.RIGHT);
		label_21.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_21.setBounds(248, 65, 40, 15);
		label_21.setText("\u0420/\u0441");

		txtBankAccount = new Text(composite_3, SWT.BORDER);
		txtBankAccount.setBounds(294, 62, 253, 21);

		Label label_22 = new Label(composite_3, SWT.NONE);
		label_22.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_22.setText("\u0423\u043B\u0438\u0446\u0430");
		label_22.setAlignment(SWT.RIGHT);
		label_22.setBounds(81, 128, 70, 15);

		txtBankStreet = new Text(composite_3, SWT.BORDER);
		txtBankStreet.setEditable(false);
		txtBankStreet.setBounds(161, 125, 387, 21);

		Label label_23 = new Label(composite_3, SWT.NONE);
		label_23.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_23.setText("\u0414\u043E\u043C");
		label_23.setAlignment(SWT.RIGHT);
		label_23.setBounds(81, 159, 70, 15);

		txtBankHouse = new Text(composite_3, SWT.BORDER);
		txtBankHouse.setEditable(false);
		txtBankHouse.setBounds(161, 154, 50, 21);

		Label label_24 = new Label(composite_3, SWT.NONE);
		label_24.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_24.setAlignment(SWT.RIGHT);
		label_24.setText("\u041E\u0444\u0438\u0441");
		label_24.setBounds(91, 191, 60, 15);

		txtBankOffice = new Text(composite_3, SWT.BORDER);
		txtBankOffice.setEditable(false);
		txtBankOffice.setBounds(161, 188, 50, 21);

		txtBankIndex = new Text(composite_3, SWT.BORDER);
		txtBankIndex.setEditable(false);
		txtBankIndex.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtBankIndex.setBounds(161, 93, 139, 21);

		Label label_27 = new Label(composite_3, SWT.NONE);
		label_27.setText("\u0418\u043D\u0434\u0435\u043A\u0441");
		label_27.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_27.setAlignment(SWT.RIGHT);
		label_27.setBounds(100, 96, 48, 15);
		
		Button btnBank = new Button(composite_3, SWT.NONE);
		btnBank.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				PartyListForm pform = new PartyListForm(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				pform.setPerson(false);
				pform.open();

				if (pform.isSelected()) {
					Party party = pform.getParty();
					company.setBank((Company) party);
					txtBankName.setText(party.getName());
					fillInBankAddress(company.getBank());
				}
			}
		});
		btnBank.setText("...");
		btnBank.setBounds(491, 26, 33, 23);

		chkEnglish = new Button(shell, SWT.CHECK);
		chkEnglish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				fillInCountryList(cboCountry);
				fillInCountryList(cboMailCountry);
				loadPartyContent();
			}
		});
		chkEnglish.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		chkEnglish.setBounds(518, 10, 64, 16);
		chkEnglish.setText("English");

		fillInCountryList(cboCountry);
		fillInCountryList(cboMailCountry);
		fillInJobList(cboJob);

		loadPartyContent();
	}

	protected void fillInBankAddress(Party bank) {
		baddr = company.getBank().findAddressByType(AddressType.WORK);
	
		if ( baddr != null) {
		    txtBankStreet.setText(baddr.getLine());
		    txtBankIndex.setText(baddr.getIndex());
		    txtBankHouse.setText(baddr.getHouse());
		    txtBankOffice.setText(baddr.getOffice());
		}
	}

	private void fillInJobList(Combo cbo) {
		cbo.removeAll();

		for (Job job : JobList.getInstance()) {
			if (chkEnglish.getSelection()) {
				cbo.add(job.getEname());
			} else {
				cbo.add(job.getName());
			}
		}
	}

	private Address getWorkAddress() {
		if (waddr == null) {
			Address addr = new Address();
			addr.setType(AddressType.WORK);
			company.getAddresses().add(addr);
			waddr = addr;
		}
		return waddr;
	}

	private Address getMailAddress() {
		if (maddr == null) {
			Address addr = new Address();
			addr.setType(AddressType.MAIL);
			company.getAddresses().add(addr);
			maddr = addr;
		}
		return maddr;
	}

	private void fillInCountryList(Combo cbo) {
		cbo.removeAll();
		List<Country> list = CountryList.getInstance();

		for (Country country : list) {
			if (chkEnglish.getSelection()) {
				cbo.add(country.getEname());
			} else {
				cbo.add(country.getName());
			}
		}
	}

	private int selectCountryInList(Country country, Combo cbo) {
		int sel = -1;

		if (country != null) {
			sel = 0;
			for (String item : cbo.getItems()) {
				if (country.getName().equals(item)
						|| country.getEname().equals(item)) {
					break;
				}
				sel++;
			}
		}
		return sel;
	}

	private int selectJobInList(Job job, Combo cbo) {
		int sel = -1;

		if (job != null) {
			sel = 0;
			for (String item : cbo.getItems()) {
				if (job.getName().equals(item) || job.getEname().equals(item)) {
					break;
				}
				sel++;
			}
		}
		return sel;
	}

	private void loadPartyContent() {
		if (company != null) {
			if (company.getPhone() != null)
				txtPhone.setText(company.getPhone().getNumber());
			if (company.getFax() != null)
				txtFax.setText(company.getFax().getNumber());
			if (company.getEmail() != null)
				txtEmail.setText(company.getEmail());
			if (company.getUnp() != null)
				txtUNP.setText(company.getUnp());
			if (company.getDirector() != null) {
				txtDirector
						.setText(company.getDirector().getPerson().getName());
				cboJob.select(selectJobInList(company.getDirector()
						.getPosition(), cboJob));
			}
			if (company.getPresenter() != null) {
				txtRepresenter.setText(company.getPresenter().getPerson().getName());
				txtRepresenterTrust.setText(company.getPresenter().getTrust());
			}
			if (company.getReciever() != null) {
				txtReciever.setText(company.getReciever().getPerson().getName());
				txtRecieverTrust.setText(company.getReciever().getTrust());
			}


			Address adr = company.findAddressByType(AddressType.WORK);
			if (adr != null)
				waddr = adr;

			adr = company.findAddressByType(AddressType.MAIL);
			if (adr != null)
				maddr = adr;

			if (chkEnglish.getSelection()) {
				txtName.setText(company.getEname());

				if (company.getAddresses().size() > 0
						&& company.getAddresses().get(0) != null) {
					if (waddr != null) {
						txtCity.setText(waddr.getEcity());
						txtIndex.setText(waddr.getIndex());
						txtStreet.setText(waddr.getEline());
						txtHouse.setText(waddr.getHouse());
						txtOffice.setText(waddr.getOffice());
						cboCountry.select(selectCountryInList(
								waddr.getCountry(), cboCountry));
					}

					if (maddr != null) {
						txtMailCity.setText(maddr.getEcity());
						txtMailIndex.setText(maddr.getIndex());
						txtMailStreet.setText(maddr.getEline());
						txtMailHouse.setText(maddr.getHouse());
						txtMailOffice.setText(maddr.getOffice());
						cboMailCountry.select(selectCountryInList(
								maddr.getCountry(), cboMailCountry));
					}
				} else {
					emptyWorkAddress();
					emptyMailAddress();

				}

			} else {
				txtName.setText(company.getName());

				if (company.getAddresses().size() > 0
						&& company.getAddresses().get(0) != null) {
					if (waddr != null) {
						txtCity.setText(waddr.getCity());
						txtIndex.setText(waddr.getIndex());
						txtStreet.setText(waddr.getLine());
						txtHouse.setText(waddr.getHouse());
						txtOffice.setText(waddr.getOffice());
						cboCountry.select(selectCountryInList(
								waddr.getCountry(), cboCountry));
					}

					if (maddr != null) {
						txtMailCity.setText(maddr.getCity());
						txtMailIndex.setText(maddr.getIndex());
						txtMailStreet.setText(maddr.getLine());
						txtMailHouse.setText(maddr.getHouse());
						txtMailOffice.setText(maddr.getOffice());
						cboMailCountry.select(selectCountryInList(
								maddr.getCountry(), cboMailCountry));
					}
				} else {
					emptyWorkAddress();
					emptyMailAddress();
				}
			}
			
			if (company.getBank() != null) { 
				txtBankName.setText(company.getBank().getName());
				txtBankCode.setText(company.getBankcode());
				txtBankAccount.setText(company.getAccount());
				fillInBankAddress(company.getBank());
			}
			

		}

	}

	private void emptyWorkAddress() {
		cboCountry.select(-1);
		txtCity.setText("");
		txtIndex.setText("");
		txtStreet.setText("");
		txtHouse.setText("");
		txtOffice.setText("");
	}

	private void emptyMailAddress() {
		cboMailCountry.select(-1);
		txtMailCity.setText("");
		txtMailIndex.setText("");
		txtMailStreet.setText("");
		txtMailHouse.setText("");
		txtMailOffice.setText("");
	}

	protected Address getAddress() {
		Address addr;
		if (company.getAddresses().size() > 0) {
			addr = company.getAddresses().get(0);
		} else {
			addr = new Address();
			company.getAddresses().add(addr);
		}
		return addr;
	}

	public void setParty(Party company) {
		this.company = (Company) company;
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
