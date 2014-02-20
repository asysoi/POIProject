package com.belcci.carnet.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;

import com.belcci.carnet.model.Company;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Person;
import com.belcci.carnet.persistence.CompanyRepository;
import com.belcci.carnet.persistence.PersonRepository;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class PartyListForm extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table tblParty;
	private boolean isPerson;
	private boolean isSelected;
	private Party party;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PartyListForm(Shell parent, int style) {
		super(parent, style);
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
		shell.setSize(661, 353);
		shell.setText("Список организаций");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 654, 287);

		tblParty = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tblParty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				selectParty();
				shell.close();
			}
		});
		tblParty.setLinesVisible(true);
		tblParty.setHeaderVisible(true);
		tblParty.setBounds(10, 26, 634, 251);

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(576, 4, 66, 23);

		ToolItem btnAdd = new ToolItem(toolBar, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				openAddPartyForm();
			}
		});
		btnAdd.setWidth(32);
		btnAdd.setText("+");

		ToolItem btnEdit = new ToolItem(toolBar, SWT.NONE);
		btnEdit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				openEditPartyForm();
			}
		});
		btnEdit.setImage(null);
		btnEdit.setText("edit");

		ToolItem btnDel = new ToolItem(toolBar, SWT.NONE);
		btnDel.setWidth(32);
		btnDel.setText("x");

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				isSelected = false;
				shell.close();
			}
		});
		btnCancel.setBounds(568, 293, 75, 25);
		btnCancel.setText("\u041E\u0442\u043C\u0435\u043D\u0438\u0442\u044C");

		Button btnSelect = new Button(shell, SWT.NONE);
		btnSelect.setText("\u0412\u044B\u0431\u0440\u0430\u0442\u044C");
		btnSelect.setBounds(485, 293, 75, 25);

		fillInTable();

		findPartyInTable();
	}

	private void findPartyInTable() {
		if (party != null) {
			int index;
			if (isPerson) {
				index = PersonRepository.getInstance().findIndexPersonByName(
						party.getName());
			} else {
				index = CompanyRepository.getInstance().findIndexCompanyByName(
						party.getName());
			}
			tblParty.select(index);
		}

	}

	protected void selectParty() {
		isSelected = true;
		if (isPerson) {
			party = PersonRepository.getInstance().getItem(
					tblParty.getSelectionIndex());
		} else {
			party = CompanyRepository.getInstance().getItem(
					tblParty.getSelectionIndex());
		}
	}

	private void fillInTable() {
		String[] titles;
		List<Party> list;

		if (isPerson) {
			titles = new String[] { "ФИО", "Адрес", "Паспорт" };
			list = PersonRepository.getInstance().getItems();
		} else {
			titles = new String[] { "Наименование", "Адрес", "Руководитель" };
			list = CompanyRepository.getInstance().getItems();
		}

		if (tblParty.getItems().length > 0) {
			tblParty.removeAll();
		}

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		tblParty.setLayoutData(data);

		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(tblParty, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(tblParty, SWT.NONE);
			item.setText(0, list.get(i).getName());
			item.setText(1, ""
					+ (list.get(i).getAddresses().size() > 0 ? list.get(i)
							.getAddresses().get(0) : ""));

			if (isPerson) {
				item.setText(
						2,
						(((Person) list.get(i)).getPassport() != null ? ((Person) list
								.get(i)).getPassport().toString() : ""));
			} else {
				// руководитель
			}
		}

		for (int i = 0; i < titles.length; i++) {
			tblParty.getColumn(i).pack();
		}

		tblParty.getColumn(0).setWidth((int) (tblParty.getSize().x * 0.4));
		tblParty.getColumn(1).setWidth((int) (tblParty.getSize().x * 0.3));
		tblParty.getColumn(2).setWidth((int) (tblParty.getSize().x * 0.3) - 4);

	}

	private void openAddPartyForm() {
		if (isPerson) {
			PersonForm pform = new PersonForm(shell, SWT.ICON_INFORMATION
					| SWT.OK);
			pform.setPerson(new Person());
			pform.open();
			if (pform.isSaved()) {
				PersonRepository.getInstance().addPerson(pform.getPerson());
				addItemToPartyTable(pform.getPerson());
			}
		} else {
			PartyForm pform = new PartyForm(shell, SWT.ICON_INFORMATION
					| SWT.OK);
			pform.setParty(new Company());
			pform.open();
			if (pform.isSaved()) {
				CompanyRepository.getInstance().addCompany(pform.getParty());
				addItemToPartyTable((Company) pform.getParty());
			}

		}

	}

	private void updatePartyTable(int index, Person person) {
		TableItem item = tblParty.getItem(index);
		item.setText(0, person.getName());
		item.setText(1, ""
				+ (person.getAddresses().size() > 0 ? person.getAddresses()
						.get(0) : ""));
		item.setText(2, (person.getPassport() != null ? person.getPassport()
				.toString() : ""));
	}

	private void updatePartyTable(int index, Company company) {
		TableItem item = tblParty.getItem(index);
		item.setText(0, company.getName());
		item.setText(1, ""
				+ (company.getAddresses().size() > 0 ? company.getAddresses()
						.get(0) : ""));
	}

	private void addItemToPartyTable(Person person) {
		TableItem item = new TableItem(tblParty, SWT.NONE);
		item.setText(0, person.getName());
		item.setText(1, ""
				+ (person.getAddresses().size() > 0 ? person.getAddresses()
						.get(0) : ""));
		item.setText(2, (person.getPassport() != null ? person.getPassport()
				.toString() : ""));
	}

	private void addItemToPartyTable(Company company) {
		TableItem item = new TableItem(tblParty, SWT.NONE);
		item.setText(0, company.getName());
		item.setText(1, ""
				+ (company.getAddresses().size() > 0 ? company.getAddresses()
						.get(0) : ""));
		// item.setText(2, "");
	}

	private void openEditPartyForm() {
		if (tblParty.getSelectionIndex() > -1) {
			if (isPerson) {
				party = PersonRepository.getInstance().getItem(
						tblParty.getSelectionIndex());
				Person person = (Person) party;

				PersonForm pform = new PersonForm(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				try {
					pform.setPerson((Person) person.clone());
				} catch (Exception ex) {
					System.out.println("Person can not be cloned.");
					ex.printStackTrace();
				}
				pform.open();
				if (pform.isSaved()) {
					PersonRepository.getInstance().set(
							tblParty.getSelectionIndex(), pform.getPerson());
					updatePartyTable(tblParty.getSelectionIndex(),
							pform.getPerson());
				}
			} else {
				party = CompanyRepository.getInstance().getItem(
						tblParty.getSelectionIndex());
				PartyForm pform = new PartyForm(shell, SWT.ICON_INFORMATION
						| SWT.OK);
				try {
					pform.setParty((Company) party.clone());
				} catch (Exception ex) {
					System.out.println("Company can not be cloned.");
					ex.printStackTrace();
				}
				pform.open();
				if (pform.isSaved()) {
					CompanyRepository.getInstance().set(
							tblParty.getSelectionIndex(), pform.getParty());
					updatePartyTable(tblParty.getSelectionIndex(),
							(Company) pform.getParty());
				}

			}
		}
	}

	public boolean isPerson() {
		return isPerson;
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

}
