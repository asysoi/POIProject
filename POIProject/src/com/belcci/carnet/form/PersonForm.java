package com.belcci.carnet.form;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.belcci.carnet.model.Address;
import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.Passport;
import com.belcci.carnet.model.Person;
import com.belcci.carnet.model.Phone;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;

public class PersonForm extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtName;
	private Text txtCity;
	private Text txtIndex;
	private Text txtLine;
	private Text txtHouse;
	private Text txtOffice;
	private Text txtCode;
	private Text txtNumber;
	private Text txtIssued;
	private Text txtPersonalid;
	private Person person;
	private Text txtLast;
	private Text txtFirst;
	private Text txtMiddle;
	private Text txtPhone;
	private Text txtFax;
	private Text txtEmail;
	private CCombo cbCountry;

	private DateTime dtIssued;
	private DateTime dtValid;
	private Button chkEnglish;
	private boolean isModified = false;
	private boolean isSaved = false;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PersonForm(Shell parent, int style) {
		super(parent, style);
		setText("Физическое лицо");
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
		shell = new Shell(getParent(), SWT.CLOSE | SWT.TITLE);
		shell.setSize(599, 409);
		shell.setText("\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u041B\u0438\u0446\u043E");

		chkEnglish = new Button(shell, SWT.CHECK);
		chkEnglish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				fillInCountryList();
				loadPersonContent();
			}
		});
		chkEnglish.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		chkEnglish.setBounds(517, 10, 66, 16);
		chkEnglish.setText("English");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(9, 36, 27, 15);
		label.setText("\u0424\u0418\u041E");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setSaved(true);
				shell.close();
			}
		});
		button.setText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		button.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button.setBounds(427, 347, 75, 25);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		button_1.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");
		button_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		button_1.setBounds(508, 347, 75, 25);

		txtName = new Text(shell, SWT.BORDER);
		txtName.setEditable(false);
		txtName.setBounds(43, 32, 540, 21);

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		tabFolder.setBounds(9, 71, 574, 261);

		TabItem tabItem_3 = new TabItem(tabFolder, SWT.NONE);
		tabItem_3.setText("\u041A\u043E\u043D\u0442\u0430\u043A\u0442\u044B");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem_3.setControl(composite);

		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setText("\u041F\u043E\u0447\u0442\u043E\u0432\u044B\u0439 \u0430\u0434\u0440\u0435\u0441");
		group_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		group_1.setBounds(273, 11, 288, 211);

		cbCountry = new CCombo(group_1, SWT.BORDER);
		cbCountry.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Address addr = getAddress();
				addr.setCountry(CountryList.getInstance().get(
						cbCountry.getSelectionIndex()));
			}
		});
		cbCountry.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		cbCountry.setBounds(64, 22, 205, 23);

		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("\u0421\u0442\u0440\u0430\u043D\u0430");
		label_7.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_7.setAlignment(SWT.RIGHT);
		label_7.setBounds(10, 25, 48, 15);

		Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setText("\u0413\u043E\u0440\u043E\u0434");
		label_11.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_11.setAlignment(SWT.RIGHT);
		label_11.setBounds(10, 58, 45, 15);

		txtCity = new Text(group_1, SWT.BORDER);
		txtCity.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Address addr = getAddress();
				if (chkEnglish.getSelection()) {
					addr.setEcity(txtCity.getText());
				} else {
					addr.setCity(txtCity.getText());
				}
			}
		});
		txtCity.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtCity.setBounds(64, 57, 205, 21);

		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setText("\u0418\u043D\u0434\u0435\u043A\u0441");
		label_12.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_12.setAlignment(SWT.RIGHT);
		label_12.setBounds(10, 91, 48, 15);

		txtIndex = new Text(group_1, SWT.BORDER);
		txtIndex.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Address addr = getAddress();
				addr.setIndex(txtIndex.getText());
			}
		});
		txtIndex.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtIndex.setBounds(64, 88, 139, 21);

		Label label_13 = new Label(group_1, SWT.NONE);
		label_13.setText("\u0423\u043B\u0438\u0446\u0430");
		label_13.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_13.setAlignment(SWT.RIGHT);
		label_13.setBounds(10, 121, 48, 15);

		txtLine = new Text(group_1, SWT.BORDER);
		txtLine.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Address addr = getAddress();
				if (chkEnglish.getSelection()) {
					addr.setEline(txtLine.getText());
				} else {
					addr.setLine(txtLine.getText());
				}
			}
		});
		txtLine.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtLine.setBounds(64, 118, 205, 21);

		Label label_14 = new Label(group_1, SWT.NONE);
		label_14.setText("\u0414\u043E\u043C");
		label_14.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_14.setAlignment(SWT.RIGHT);
		label_14.setBounds(10, 152, 48, 15);

		txtHouse = new Text(group_1, SWT.BORDER);
		txtHouse.addVerifyListener(new DigitalListener());
		txtHouse.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Address addr = getAddress();
				if (chkEnglish.getSelection()) {
					addr.setHouse(txtHouse.getText());
				} else {
					addr.setHouse(txtHouse.getText());
				}
			}
		});
		txtHouse.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtHouse.setBounds(64, 149, 76, 21);

		Label label_15 = new Label(group_1, SWT.NONE);
		label_15.setText("\u041A\u0432\u0430\u0440\u0442\u0438\u0440\u0430");
		label_15.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		label_15.setAlignment(SWT.RIGHT);
		label_15.setBounds(3, 183, 55, 15);

		txtOffice = new Text(group_1, SWT.BORDER);
		txtOffice.addVerifyListener(new DigitalListener());
		txtOffice.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Address addr = getAddress();
				if (chkEnglish.getSelection()) {
					addr.setOffice(txtOffice.getText());
				} else {
					addr.setOffice(txtOffice.getText());
				}
			}
		});
		txtOffice.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtOffice.setBounds(64, 180, 76, 21);

		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		label_8.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_8.setAlignment(SWT.RIGHT);
		label_8.setBounds(10, 23, 56, 15);

		txtLast = new Text(composite, SWT.BORDER);
		txtLast.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					person.setElastname(txtLast.getText());
					txtName.setText(person.getEname());
				} else {
					person.setLastname(txtLast.getText());
					txtName.setText(person.getName());
				}
			}
		});
		txtLast.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtLast.setBounds(72, 21, 180, 21);

		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("\u0418\u043C\u044F");
		label_9.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_9.setAlignment(SWT.RIGHT);
		label_9.setBounds(35, 54, 31, 15);

		txtFirst = new Text(composite, SWT.BORDER);
		txtFirst.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					person.setEfirstname(txtFirst.getText());
					txtName.setText(person.getEname());
				} else {
					person.setFirstname(txtFirst.getText());
					txtName.setText(person.getName());
				}
			}
		});
		txtFirst.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtFirst.setBounds(72, 52, 180, 21);

		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setText("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
		label_10.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_10.setAlignment(SWT.RIGHT);
		label_10.setBounds(10, 84, 56, 15);

		txtMiddle = new Text(composite, SWT.BORDER);
		txtMiddle.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (chkEnglish.getSelection()) {
					person.setEmiddlename(txtMiddle.getText());
					txtName.setText(person.getEname());
				} else {
					person.setMiddlename(txtMiddle.getText());
					txtName.setText(person.getName());
				}
			}
		});
		txtMiddle.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtMiddle.setBounds(72, 82, 180, 21);

		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setText("\u0422\u0435\u043B");
		label_16.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_16.setAlignment(SWT.RIGHT);
		label_16.setBounds(33, 111, 33, 15);

		txtPhone = new Text(composite, SWT.BORDER);
		txtPhone.addVerifyListener(new PhoneListener());
		txtPhone.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (person.getPhone() == null) {
					Phone phone = new Phone();
					phone.setNumber(txtPhone.getText());
					person.setPhone(phone);
				} else {
					person.getPhone().setNumber(txtPhone.getText());
				}
			}
		});
		txtPhone.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtPhone.setBounds(72, 109, 180, 21);

		Label label_17 = new Label(composite, SWT.NONE);
		label_17.setText("\u0424\u0430\u043A\u0441");
		label_17.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_17.setAlignment(SWT.RIGHT);
		label_17.setBounds(25, 138, 41, 15);

		txtFax = new Text(composite, SWT.BORDER);
		txtFax.addVerifyListener(new PhoneListener());
		txtFax.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (person.getFax() == null) {
					Phone phone = new Phone();
					phone.setNumber(txtFax.getText());
					person.setFax(phone);
				} else {
					person.getFax().setNumber(txtFax.getText());
				}
			}
		});
		txtFax.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtFax.setBounds(72, 140, 180, 21);

		txtEmail = new Text(composite, SWT.BORDER);
		txtEmail.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				person.setEmail(txtEmail.getText());
			}
		});
		txtEmail.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		txtEmail.setBounds(72, 170, 180, 21);

		Label label_18 = new Label(composite, SWT.NONE);
		label_18.setText("Email");
		label_18.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_18.setAlignment(SWT.RIGHT);
		label_18.setBounds(33, 172, 33, 15);

		TabItem tabItem_2 = new TabItem(tabFolder, 0);
		tabItem_2.setText("\u041F\u0430\u0441\u043F\u043E\u0440\u0442");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tabItem_2.setControl(composite_2);

		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("\u0421\u0435\u0440\u0438\u044F ");
		label_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(70, 32, 55, 15);

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("\u0414\u0430\u0442\u0430 \u0432\u044B\u0434\u0430\u0447\u0438");
		label_2.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(42, 63, 83, 15);

		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u0442\u0435\u043B\u0435\u043D \u0434\u043E");
		label_3.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_3.setAlignment(SWT.RIGHT);
		label_3.setBounds(10, 94, 115, 15);

		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setText("\u0412\u044B\u0434\u0430\u043D");
		label_4.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_4.setAlignment(SWT.RIGHT);
		label_4.setBounds(70, 125, 55, 15);

		Label label_5 = new Label(composite_2, SWT.NONE);
		label_5.setText("\u041B\u0438\u0447\u043D\u044B\u0439 \u043D\u043E\u043C\u0435\u0440");
		label_5.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(35, 156, 90, 15);

		txtCode = new Text(composite_2, SWT.BORDER);
		txtCode.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Passport pass = getPassport();
				pass.setCode(txtCode.getText());
			}
		});
		txtCode.setBounds(128, 30, 66, 21);

		Label label_6 = new Label(composite_2, SWT.NONE);
		label_6.setText("\u041D\u043E\u043C\u0435\u0440");
		label_6.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label_6.setAlignment(SWT.RIGHT);
		label_6.setBounds(200, 32, 55, 15);

		txtNumber = new Text(composite_2, SWT.BORDER);
		txtNumber.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Passport pass = getPassport();
				pass.setNumber(txtNumber.getText());
			}
		});
		txtNumber.setBounds(261, 30, 158, 21);

		dtIssued = new DateTime(composite_2, SWT.BORDER);
		dtIssued.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Passport pass = getPassport();	
				pass.setIssdate(new GregorianCalendar(dtIssued.getYear(), dtIssued.getMonth(),
						dtIssued.getDay()));
			}
		});
		
		dtIssued.setBounds(129, 60, 114, 24);

		dtValid = new DateTime(composite_2, SWT.BORDER);
		dtValid.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				Passport pass = getPassport();
				pass.setValiddate(new GregorianCalendar(dtValid.getYear(), dtValid.getMonth(),
						dtValid.getDay()));
			}
		});
		dtValid.setBounds(129, 90, 114, 24);

		txtIssued = new Text(composite_2, SWT.BORDER);
		txtIssued.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Passport pass = getPassport();
				if (chkEnglish.getSelection()) {
					pass.setEissued(txtIssued.getText());
				} else {
					pass.setIssued(txtIssued.getText());
				}
			}
		});
		txtIssued.setBounds(130, 124, 289, 21);

		txtPersonalid = new Text(composite_2, SWT.BORDER);
		txtPersonalid.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				Passport pass = getPassport();
				pass.setPersonalid(txtPersonalid.getText());
			}
		});
		txtPersonalid.setBounds(131, 156, 288, 21);

		fillInCountryList();
		loadPersonContent();
		isModified = false;
	}

	protected Passport getPassport() {
		Passport pass;
		if (person.getPassport() != null) {
			pass = person.getPassport();
		} else {
			pass = new Passport();
			person.setPassport(pass);
		}
        return pass;
	}

	protected Address getAddress() {
		Address addr;
		if (person.getAddresses().size() > 0) {
			addr = person.getAddresses().get(0);
		} else {
			addr = new Address();
			person.getAddresses().add(addr);
		}
		return addr;
	}

	private void fillInCountryList() {
		cbCountry.removeAll();
		List<Country> list = CountryList.getInstance();

		for (Country country : list) {
			if (chkEnglish.getSelection()) {
				cbCountry.add(country.getEname());
			} else {
				cbCountry.add(country.getName());
			}
		}
	}

	private int selectCountryInList(Country country) {
		int sel = -1;

		if (country != null) {
			sel = 0;
			for (String item : cbCountry.getItems()) {
				if (country.getName().equals(item)
						|| country.getEname().equals(item)) {
					break;
				}
				sel++;
			}
		}
		return sel;
	}

	private void loadPersonContent() {
		if (person != null) {
			if (chkEnglish.getSelection()) {
				txtName.setText(person.getEname());
				txtFirst.setText(person.getEfirstname());
				txtLast.setText(person.getElastname());
				txtMiddle.setText(person.getEmiddlename());

				if (person.getAddresses().size() > 0
						&& person.getAddresses().get(0) != null) {
					Address adr = person.getAddresses().get(0);
					txtCity.setText(adr.getEcity());
					txtIndex.setText(adr.getIndex());
					txtLine.setText(adr.getEline());
					txtHouse.setText(adr.getHouse());
					txtOffice.setText(adr.getOffice());
				} else {
					emptyAddress();
				}

				if (person.getPassport() != null) {
					Passport pas = person.getPassport();
					txtCode.setText(pas.getCode());
					txtNumber.setText(pas.getNumber());
					txtIssued.setText(pas.getEissued());
					txtPersonalid.setText(pas.getPersonalid());
					if (pas.getIssdate() != null) {
						dtIssued.setDate(pas.getIssdate().get(Calendar.YEAR),
								pas.getIssdate().get(Calendar.MONTH), 
								pas.getIssdate().get(Calendar.DAY_OF_MONTH));
					}
					if (pas.getValiddate() != null) {
						dtValid.setDate(pas.getValiddate().get(Calendar.YEAR),
								pas.getValiddate().get(Calendar.MONTH),
								pas.getValiddate().get(Calendar.DAY_OF_MONTH));
					}
				}
			} else {
				txtName.setText(person.getName());
				txtFirst.setText(person.getFirstname());
				txtLast.setText(person.getLastname());
				txtMiddle.setText(person.getMiddlename());

				if (person.getAddresses().size() > 0
						&& person.getAddresses().get(0) != null) {
					Address adr = person.getAddresses().get(0);
					txtCity.setText(adr.getCity());
					txtIndex.setText(adr.getIndex());
					txtLine.setText(adr.getLine());
					txtHouse.setText(adr.getHouse());
					txtOffice.setText(adr.getOffice());
				} else {
					emptyAddress();
				}

				if (person.getPassport() != null) {
					Passport pas = person.getPassport();
					txtCode.setText(pas.getCode());
					txtNumber.setText(pas.getNumber());
					txtIssued.setText(pas.getIssued());
					txtPersonalid.setText(pas.getPersonalid());

					if (pas.getIssdate() != null) {
						dtIssued.setDate(pas.getIssdate().get(Calendar.YEAR),
								pas.getIssdate().get(Calendar.MONTH), pas
										.getIssdate()
										.get(Calendar.DAY_OF_MONTH));
					}
					if (pas.getValiddate() != null) {
						dtValid.setDate(pas.getValiddate().get(Calendar.YEAR),
								pas.getValiddate().get(Calendar.MONTH),
								pas.getValiddate().get(Calendar.DAY_OF_MONTH));
					}
				}
			}
			if (person.getPhone() != null) {
				txtPhone.setText(person.getPhone().getNumber());
			}
			if (person.getFax() != null) {
				txtFax.setText(person.getFax().getNumber());
			}
			if (person.getEmail() != null) {
				txtEmail.setText(person.getEmail());
			}
			Address addr = getAddress();
			cbCountry.select(selectCountryInList(addr.getCountry()));
		}
	}

	private void emptyAddress() {
		cbCountry.select(-1);
		txtCity.setText("");
		txtIndex.setText("");
		txtLine.setText("");
		txtHouse.setText("");
		txtOffice.setText("");
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
}
