package com.belcci.carnet.form;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Address;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.belcci.carnet.builder.CarnetPDFBuilder;
import com.belcci.carnet.command.AddHolder;
import com.belcci.carnet.command.Command;
import com.belcci.carnet.config.CarnetConfig;
import com.belcci.carnet.config.CarnetConfigurator;
import com.belcci.carnet.config.ConfigReader;
import com.belcci.carnet.config.XMLConfigReader;
import com.belcci.carnet.form.CarnetApplication.LoadConfig;
import com.belcci.carnet.form.CarnetApplication.Open;
import com.belcci.carnet.form.CarnetApplication.Print;
import com.belcci.carnet.form.CarnetApplication.PrintPDF;
import com.belcci.carnet.form.CarnetApplication.Save;
import com.belcci.carnet.load.CarnetReader;
import com.belcci.carnet.load.WordReader;
import com.belcci.carnet.model.CarnetATA;
import com.belcci.carnet.model.CarnetIterator;
import com.belcci.carnet.model.CarnetList;
import com.belcci.carnet.model.CarnetNumber;
import com.belcci.carnet.model.Company;
import com.belcci.carnet.model.Country;
import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.CountryQuantity;
import com.belcci.carnet.model.Employer;
import com.belcci.carnet.model.Job;
import com.belcci.carnet.model.JobList;
import com.belcci.carnet.model.Party;
import com.belcci.carnet.model.Passport;
import com.belcci.carnet.model.Person;
import com.belcci.carnet.model.ProductItem;
import com.belcci.carnet.model.ProductType;
import com.belcci.carnet.model.Target;
import com.belcci.carnet.persistence.CompanyRepository;
import com.belcci.carnet.persistence.CompanyRepositoryLoader;
import com.belcci.carnet.persistence.PersonRepository;
import com.belcci.carnet.persistence.PersonRepositoryLoader;
import com.belcci.carnet.persistence.ProductTypeList;
import com.belcci.carnet.persistence.TargetList;
import com.belcci.otrs.util.DesEncrypter;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

public class CarnetApplication {
	public static final String M_LOGIN = "Войти";
	public static final String M_LOGOFF = "Выйти";
	public static final String M_TICKETS = "Список новых заявок";
	public static final String M_LOG = "Журнал событий";
	public static final String M_REPORT = "Отчет";
	public static final String M_LINK = "Перейти в OTRS";
	public static final String M_PROPERTY = "Параметры";
	public static final String M_EXIT = "Закрыть";
	public static final String APP_TIPS = "Электронная заявка";
	public static final String TAG_ERROR = "Error";
	public static final String TAG_SESSIONCREATE_RESP = "SessionCreateResponse";
	public static final String TAG_SESSIONID = "SessionID";
	public static final String TAG_TICKET_SEARCH_RESP = "TicketSearchResponse";
	public static final String TAG_TICKETID = "TicketID";
	public static final String TAG_TICKETGET_RESP = "TicketGetResponse";
	public static final String OPR_TICKET_SEARCH = "TicketSearch";
	public static final String OPR_SESSION_CREATE = "SessionCreate";
	public static final String OPR_TICKET_GET = "TicketGet";
	public static final String TAG_USERLOGIN = "UserLogin";
	public static final String TAG_PASSWORD = "Password";
	public static final String PR_LOGIN = "Login";
	public static final String PR_PSW = "Password";
	public static final String PR_LOGIN_SAVE = "SaveLogin";
	public static final String PR_CHEK_MYQUEUE = "CheckMyQueue";
	public static final String PR_REMINDER = "Reminder";
	public static final String PR_SINGLE_WINDOW = "SingleWindow";
	public static final String PR_SOUND = "Sound";
	public static final String PR_CONFIRMATION = "Confirmation";
	public static final String PR_CHECK_TIME = "CheckTime";
	public static final String PR_LAST_TICKETID = "LastTicketID";
	public static final String PR_WEBSERVICE_NAMESPACE = "OTRSWebserviceNamespace";
	public static final String PR_WEBSERVICE_URL = "OTRSWebserviceURL";
	public static final String VL_STATE_NEW = "new";
	public static final String TAG_STATE = "State";
	public static final String TAG_STATES = "States";
	public static final String PR_AUTOLOGIN = "AutoLogin";
	public static final String PR_OTRS_URL = "OTRS_URL";
	public static final String PR_PATH = "BrowserPath";
	public static final String PR_PAGE_CONFIG_FILE = "PageConfigurationFile";
	public static final String PR_COMPANY_REPO_FILE = "CompanyRepositiryFile";
	public static final String PR_PERSON_REPO_FILE = "PersonRepositoryFile";
	
	public static Logger LOG = Logger.getLogger("CarnetATA");
	private final String STR_COUNTRYTARGET = "Страна назначения";
	private final String STR_COUNTRYTRANSIT = "Страна транзита";
	private Party holderParty;
	private Party repParty;
	private Display display;
	private CarnetATA carnet;
	private CarnetList carnets = CarnetList.getInstance();
	private Shell shell;
	private String printerselected;
	private String carnetConfigFileName;
	private Browser browser;
	private OleClientSite clientSite;
	private OleFrame frame;
	private Menu menu;
	private List listCarnet;
	private int carnetNumber = 1;
	private int currentNumber = 0;
	private Table tblGoods;
	private CLabel textNumber;
	private TabFolder tabFolder;
	private Composite reportForm;
	private Button chkPerson;
	private Button chkRepPerson;
	private Composite composite;
	private Label lblHolderParty;
	private Label lblRepParty;

	private DateTime dtExit;
	private DateTime dtStart;
	private DateTime dtEnd;
	private DateTime dtIssued;
	private DateTime dtValid;
	private DateTime dtReturn;

	private boolean isModifiable = true;
	private Table tblReport;
	private Label txtDescription;
	private Label txtRepDescription;
	private Table tableTarget;
	private Table tableTransit;
	private Text txtValue;
	private Text txtFee;
	private Text txtValBYRToUSD;
	private Text txtAddPages;
	private Button chkReturn;
	private Button chkIssued;
	private CCombo cbTarget;
	private CCombo cbType;
	private CCombo cbCurrency;
	private Text txtHolderParty;
	private Text txtRepParty;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CarnetApplication app = new CarnetApplication();
			app.init();
			app.open();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.getMessage());
		}
	}
	
	/**
	 * Init application
	 */
	private void init() {
		try {
			LOG.info("Read application config file");
			CarnetConfig props = CarnetConfig.getInstance();
			// load carnet config
			props.setProperties(CarnetConfigurator.getInstance().readConfig());
			// password decryption
			DesEncrypter encrypter = new DesEncrypter();
			props.setProperty(CarnetApplication.PR_PSW, encrypter.decrypt(props.getProperty(CarnetApplication.PR_PSW)));
			// load page configuration
			loadPageConfig(props.getProperty(CarnetApplication.PR_PAGE_CONFIG_FILE));
			// load company repository
			CompanyRepositoryLoader.getInstance(props.getProperty(CarnetApplication.PR_COMPANY_REPO_FILE)).load();
			// load person repository
			PersonRepositoryLoader.getInstance(props.getProperty(CarnetApplication.PR_PERSON_REPO_FILE)).load();
		} catch (FileNotFoundException ex) {
			LOG.warn("Configuration file is not available ...");
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.pack();
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
		shell.setSize(782, 670);
		shell.setMinimumSize(new Point(370, 670));
		shell.setText("Carnet Application");

		reportForm = new Composite(shell, SWT.NONE);
		reportForm.setLayout(new FillLayout(SWT.HORIZONTAL));

		tblReport = new Table(reportForm, SWT.BORDER | SWT.FULL_SELECTION);
		tblReport.setHeaderVisible(true);
		tblReport.setLinesVisible(true);

		menu = new Menu(shell, SWT.BAR);
		// create a file menu and add an exit item
		final MenuItem fcarnet = new MenuItem(menu, SWT.CASCADE);
		fcarnet.setText("&Карнет");
		final Menu carnetmenu = new Menu(shell, SWT.DROP_DOWN);
		fcarnet.setMenu(carnetmenu);

		MenuItem mnNewCarnet = new MenuItem(carnetmenu, SWT.NONE);
		mnNewCarnet.setText("\u041D\u043E\u0432\u044B\u0439");
		mnNewCarnet.addSelectionListener(new NewCarnet());

		MenuItem menuItem = new MenuItem(carnetmenu, SWT.SEPARATOR);

		MenuItem mnCarnetList = new MenuItem(carnetmenu, SWT.NONE);
		mnCarnetList
				.setText("\u0421\u043F\u0438\u0441\u043E\u043A \u041A\u0430\u0440\u043D\u0435\u0442\u043E\u0432");

		final MenuItem mnLoadProducts = new MenuItem(carnetmenu, SWT.PUSH);
		mnLoadProducts
				.setText("&\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u041F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u044E\tCTRL+O");
		mnLoadProducts.setAccelerator(SWT.CTRL + 'O');

		final MenuItem mnCreatePDF = new MenuItem(carnetmenu, SWT.PUSH);
		mnCreatePDF.setText("&Сгенерировать Карнет\tCTRL+S");
		mnCreatePDF.setAccelerator(SWT.CTRL + 'S');

		new MenuItem(carnetmenu, SWT.SEPARATOR);

		final MenuItem printItem = new MenuItem(carnetmenu, SWT.PUSH);
		printItem.setText("&Печать\tCTRL+P");
		printItem.setAccelerator(SWT.CTRL + 'P');

		final MenuItem printPDFItem = new MenuItem(carnetmenu, SWT.PUSH);
		printPDFItem
				.setText("&\u041F\u0435\u0447\u0430\u0442\u044C \u041A\u0430\u0440\u043D\u0435\u0442\u0430 PDF\tCTRL+P");
		printPDFItem.setAccelerator(SWT.CTRL + 'P');

		new MenuItem(carnetmenu, SWT.SEPARATOR);

		final MenuItem mnConfigItem = new MenuItem(carnetmenu, SWT.PUSH);
		mnConfigItem
				.setText("&\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u041A\u043E\u043D\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044E \u0421\u0442\u0440\u0430\u043D\u0438\u0446\tCTRL+Z");
		mnConfigItem.setAccelerator(SWT.CTRL + 'L');

		//MenuItem mnCompanyRepository = new MenuItem(carnetmenu, SWT.NONE);
		//mnCompanyRepository
		//		.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0421\u043F\u0438\u0441\u043E\u043A \u041A\u043E\u043C\u043F\u0430\u043D\u0438\u0439");

		//MenuItem mnPersonRepository = new MenuItem(carnetmenu, SWT.NONE);
		//mnPersonRepository
		//		.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044C \u0421\u043F\u0438\u0441\u043E\u043A \u0424\u0438\u0437\u043B\u0438\u0446");

		final MenuItem separator = new MenuItem(carnetmenu, SWT.SEPARATOR);
		final MenuItem exitItem = new MenuItem(carnetmenu, SWT.PUSH);
		exitItem.setText("E&xit");

		mnLoadProducts.addSelectionListener(new Open());
		mnCreatePDF.addSelectionListener(new Save());
		printItem.addSelectionListener(new Print());
		printPDFItem.addSelectionListener(new PrintPDF());
		mnConfigItem.addSelectionListener(new LoadConfig());

		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
						| SWT.YES | SWT.NO);
				messageBox.setMessage("Завершить работу приложения?");
				messageBox.setText(" Выход");
				int response = messageBox.open();

				if (response == SWT.YES) {
					carnet = null;
					System.exit(0);
				}
			}
		});

		final StackLayout stackLayout = new StackLayout();
		shell.setLayout(stackLayout);
		shell.setMenuBar(menu);

		MenuItem mnReport = new MenuItem(menu, SWT.CASCADE);
		mnReport.setText("\u041E\u0442\u0447\u0435\u0442");

		Menu reportmenu = new Menu(mnReport);
		mnReport.setMenu(reportmenu);

		final SashForm carnetForm = new SashForm(shell, SWT.SMOOTH);

		MenuItem mnReportOpen = new MenuItem(reportmenu, SWT.NONE);
		mnReportOpen.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				reportForm.setVisible(true);
				carnetForm.setVisible(false);
				stackLayout.topControl = reportForm;
				fillInCarnetReportTable(carnets);
			}
		});
		mnReportOpen.setText("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");

		mnCarnetList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				reportForm.setVisible(false);
				carnetForm.setVisible(true);
				stackLayout.topControl = carnetForm;
			}
		});

		Rectangle clientArea = shell.getClientArea();
		shell.setBounds(clientArea.x + 10, clientArea.y + 10, 300, 200);

		listCarnet = new List(carnetForm, SWT.BORDER | SWT.V_SCROLL);
		listCarnet.addSelectionListener(new CarnetSelect());
		tabFolder = new TabFolder(carnetForm, SWT.NONE);

		TabItem tabCarnet = new TabItem(tabFolder, SWT.NONE);
		tabCarnet.setText("\u0421\u0432\u043E\u0439\u0441\u0442\u0432\u0430");

		composite = new Composite(tabFolder, SWT.NONE);
		composite.setEnabled(false);
		tabCarnet.setControl(composite);
		composite.setLayout(null);

		Label lblNewLabel = new Label(composite, SWT.SHADOW_NONE);
		lblNewLabel.setBounds(411, 358, 174, 33);

		TabFolder tabProperty = new TabFolder(composite, SWT.NONE);
		tabProperty.setBounds(23, 369, 555, 195);

		TabItem tabDate = new TabItem(tabProperty, SWT.NONE);
		tabDate.setText("\u0426\u0435\u043B\u0438 \u0438 \u0421\u0440\u043E\u043A\u0438");

		Composite composite_1 = new Composite(tabProperty, SWT.NONE);
		tabDate.setControl(composite_1);

		Label lblTarget = new Label(composite_1, SWT.NONE);
		lblTarget.setAlignment(SWT.RIGHT);
		lblTarget.setBounds(9, 19, 82, 15);
		lblTarget
				.setText("\u0426\u0435\u043B\u044C \u0432\u044B\u0432\u043E\u0437\u0430");

		cbTarget = new CCombo(composite_1, SWT.BORDER);
		cbTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LOG.debug("Target selected ");
				if (isModifiable) {
					carnet.setTarget(TargetList.getInstance().findTargetByName(
							cbTarget.getText()));
				}
			}
		});
		cbTarget.setBounds(97, 14, 427, 23);

		Label lblType = new Label(composite_1, SWT.NONE);
		lblType.setAlignment(SWT.RIGHT);
		lblType.setText("\u0422\u0438\u043F \u043F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u0438");
		lblType.setBounds(8, 50, 84, 15);

		cbType = new CCombo(composite_1, SWT.BORDER);
		cbType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LOG.debug("cbType selected ");
				if (isModifiable) {
					carnet.setProductType(ProductTypeList.getInstance()
							.findProductTypeByName(cbType.getText()));
				}
			}
		});
		cbType.setBounds(97, 45, 427, 23);

		Label lblExitDate = new Label(composite_1, SWT.NONE);
		lblExitDate.setAlignment(SWT.RIGHT);
		lblExitDate.setBounds(10, 89, 98, 15);
		lblExitDate
				.setText("\u0414\u0430\u0442\u0430 \u0432\u044B\u0435\u0437\u0434\u0430 \u0438\u0437 \u0420\u0411");

		dtStart = new DateTime(composite_1, SWT.BORDER);
		dtStart.setBounds(218, 83, 88, 24);

		Label lblTo = new Label(composite_1, SWT.NONE);
		lblTo.setBounds(309, 87, 17, 15);
		lblTo.setText("\u043F\u043E ");

		dtEnd = new DateTime(composite_1, SWT.BORDER);
		dtEnd.setBounds(329, 83, 89, 24);

		dtExit = new DateTime(composite_1, SWT.BORDER);
		dtExit.setBounds(116, 83, 85, 24);

		Label lblFrom = new Label(composite_1, SWT.NONE);
		lblFrom.setText("c");
		lblFrom.setBounds(206, 88, 8, 15);

		dtIssued = new DateTime(composite_1, SWT.BORDER);
		dtIssued.setEnabled(false);
		dtIssued.setBounds(115, 113, 86, 24);
		dtValid = new DateTime(composite_1, SWT.BORDER);
		dtValid.setBounds(329, 113, 89, 24);

		Label lblValid = new Label(composite_1, SWT.NONE);
		lblValid.setText("\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u0442\u0435\u043B\u0435\u043D \u0434\u043E");
		lblValid.setAlignment(SWT.RIGHT);
		lblValid.setBounds(203, 118, 119, 15);

		chkIssued = new Button(composite_1, SWT.CHECK);
		chkIssued.setBounds(10, 117, 93, 16);
		chkIssued
				.setText("\u041A\u0430\u0440\u043D\u0435\u0442 \u0432\u044B\u0434\u0430\u043D");

		chkReturn = new Button(composite_1, SWT.CHECK);
		chkReturn.setBounds(10, 146, 126, 16);
		chkReturn.setFont(SWTResourceManager.getFont("Arial", 9, SWT.NORMAL));
		chkReturn
				.setText("\u041A\u0430\u0440\u043D\u0435\u0442 \u0432\u043E\u0437\u0432\u0440\u0430\u0449\u0435\u043D");

		dtReturn = new DateTime(composite_1, SWT.BORDER);
		dtReturn.setEnabled(false);
		dtReturn.setBounds(142, 143, 76, 24);
		dtEnd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				carnet.setDtEnd(dtEnd.getYear(), dtEnd.getMonth(),
						dtEnd.getDay());
			}
		});
		dtStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				carnet.setDtStart(dtStart.getYear(), dtStart.getMonth(),
						dtStart.getDay());
			}
		});
		dtExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				carnet.setDtExit(dtExit.getYear(), dtExit.getMonth(),
						dtExit.getDay());
			}
		});

		dtValid.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				carnet.setDtValid(dtValid.getYear(), dtValid.getMonth(),
						dtValid.getDay());
			}
		});

		dtReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				carnet.setDtReturn(dtReturn.getYear(), dtReturn.getMonth(),
						dtReturn.getDay());
			}
		});

		TabItem tsbTarget = new TabItem(tabProperty, SWT.NONE);
		tsbTarget
				.setText("\u0421\u0442\u0440\u0430\u043D\u044B \u043D\u0430\u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044F");

		Composite composite_3 = new Composite(tabProperty, SWT.NONE);
		tsbTarget.setControl(composite_3);

		tableTarget = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		tableTarget.setBounds(10, 26, 514, 121);
		tableTarget.setHeaderVisible(true);
		tableTarget.setLinesVisible(true);

		ToolBar toolTargetBar = new ToolBar(composite_3, SWT.FLAT | SWT.RIGHT);
		toolTargetBar.setBounds(434, 3, 89, 23);

		ToolItem btTargetAdd = new ToolItem(toolTargetBar, SWT.NONE);
		btTargetAdd.setText("add");
		ToolItem btTargetEdit = new ToolItem(toolTargetBar, SWT.NONE);
		btTargetEdit.setText("edit");
		ToolItem btTargetDel = new ToolItem(toolTargetBar, SWT.NONE);
		btTargetDel.setText("del");

		btTargetAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				addCountryToTable(tableTarget, carnet.getTargetCountries(),
						STR_COUNTRYTARGET);
			}
		});
		btTargetEdit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				editCountryToTable(tableTarget, carnet.getTargetCountries(),
						STR_COUNTRYTARGET);
			}
		});
		btTargetDel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				delCountryFromTable(tableTarget);
			}
		});

		tableTarget.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent arg0) {
				editCountryToTable(tableTarget, carnet.getTargetCountries(),
						STR_COUNTRYTARGET);

			}
		});

		TabItem tabTransit = new TabItem(tabProperty, SWT.NONE);
		tabTransit
				.setText("\u0421\u0442\u0440\u0430\u043D\u044B \u0442\u0440\u0430\u043D\u0437\u0438\u0442\u0430");
		Composite composite_4 = new Composite(tabProperty, SWT.NONE);
		tabTransit.setControl(composite_4);

		tableTransit = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		tableTransit.setBounds(10, 26, 514, 121);
		tableTransit.setHeaderVisible(true);
		tableTransit.setLinesVisible(true);

		ToolBar toolTransitBar = new ToolBar(composite_4, SWT.FLAT | SWT.RIGHT);
		toolTransitBar.setBounds(434, 3, 89, 23);

		ToolItem btTransitAdd = new ToolItem(toolTransitBar, SWT.NONE);
		btTransitAdd.setText("add");

		ToolItem btTransitEdit = new ToolItem(toolTransitBar, SWT.NONE);
		btTransitEdit.setText("edit");

		ToolItem btTransitDel = new ToolItem(toolTransitBar, SWT.NONE);
		btTransitDel.setText("del");

		btTransitAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				addCountryToTable(tableTransit, carnet.getTransitCountries(),
						STR_COUNTRYTRANSIT);
			}
		});
		btTransitEdit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				editCountryToTable(tableTransit, carnet.getTransitCountries(),
						STR_COUNTRYTRANSIT);
			}
		});
		btTransitDel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				delCountryFromTable(tableTransit);
			}
		});

		tableTransit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				editCountryToTable(tableTransit, carnet.getTransitCountries(),
						STR_COUNTRYTRANSIT);
			}
		});

		TabItem tabValue = new TabItem(tabProperty, SWT.NONE);
		tabValue.setText("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C");

		Composite composite_2 = new Composite(tabProperty, SWT.NONE);
		tabValue.setControl(composite_2);

		Label lblValue = new Label(composite_2, SWT.RIGHT);
		lblValue.setBounds(10, 24, 122, 15);
		lblValue.setText("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C \u0442\u043E\u0432\u0430\u0440\u043E\u0432");

		txtValue = new Text(composite_2, SWT.BORDER);
		txtValue.addVerifyListener(new DigitalListener());
		txtValue.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				LOG.debug("Value: " + txtValue.getText());
				if (isModifiable) {
					carnet.getGoodsTotal().setValue(
							Double.parseDouble(txtValue.getText()));
				}
			}
		});
		txtValue.setBounds(135, 21, 196, 21);

		Label lblCarnetValue = new Label(composite_2, SWT.NONE);
		lblCarnetValue
				.setText("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C \u0443\u0441\u043B\u0443\u0433");
		lblCarnetValue.setAlignment(SWT.RIGHT);
		lblCarnetValue.setBounds(10, 90, 122, 15);

		txtFee = new Text(composite_2, SWT.BORDER);
		txtFee.setBounds(135, 88, 133, 21);
		txtFee.addVerifyListener(new DigitalListener());
		txtFee.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				LOG.debug("Fee: " + txtFee.getText());
				if (isModifiable) {
					carnet.getGoodsTotal().setFee(
							Double.parseDouble(txtFee.getText()));
				}
			}
		});

		Label lblValuExUSD = new Label(composite_2, SWT.NONE);
		lblValuExUSD.setAlignment(SWT.RIGHT);
		lblValuExUSD.setBounds(10, 57, 121, 15);
		lblValuExUSD
				.setText("\u041F\u0435\u0440\u0435\u0441\u0447\u0435\u0442 \u0432 USD");

		txtValBYRToUSD = new Text(composite_2, SWT.BORDER);
		txtValBYRToUSD.setEditable(false);
		txtValBYRToUSD.setBounds(136, 55, 101, 21);

		Label lblAddPages = new Label(composite_2, SWT.NONE);
		lblAddPages.setAlignment(SWT.RIGHT);
		lblAddPages.setBounds(46, 125, 85, 15);
		lblAddPages
				.setText("\u0414\u043E\u043F. \u043B\u0438\u0441\u0442\u044B");

		txtAddPages = new Text(composite_2, SWT.BORDER);
		txtAddPages.setEditable(false);
		txtAddPages.setBounds(136, 123, 76, 21);

		cbCurrency = new CCombo(composite_2, SWT.BORDER);
		cbCurrency.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				carnet.getGoodsTotal().setCurrency(
						Currency.getInstance(cbCurrency.getText()));
			}
		});
		cbCurrency.setBounds(344, 21, 85, 21);

		Label lblNumber = new Label(composite, SWT.NONE);
		lblNumber.setBounds(22, 19, 39, 22);
		lblNumber.setFont(SWTResourceManager.getFont("Arial CYR", 10,
				SWT.NORMAL));
		lblNumber.setText("\u041D\u043E\u043C\u0435\u0440");

		Group grpHolder = new Group(composite, SWT.NONE);
		grpHolder
				.setText("\u0414\u0435\u0440\u0436\u0430\u0442\u0435\u043B\u044C");
		grpHolder.setFont(SWTResourceManager.getFont("Arial CYR", 10,
				SWT.NORMAL));
		grpHolder.setBounds(22, 47, 552, 147);

		Button bnHolderEdit = new Button(grpHolder, SWT.NONE);
		bnHolderEdit.setImage(SWTResourceManager.getImage(
				CarnetApplication.class,
				"/com/sun/java/swing/plaf/windows/icons/Directory.gif"));
		bnHolderEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				openPartyListForm(chkPerson.getSelection(), true,
						txtHolderParty, txtDescription);
			}
		});
		bnHolderEdit.setBounds(511, 44, 30, 25);

		txtHolderParty = new Text(grpHolder, SWT.BORDER | SWT.READ_ONLY);
		txtHolderParty.setBounds(101, 44, 410, 26);

		lblHolderParty = new Label(grpHolder, SWT.NONE);
		lblHolderParty.setAlignment(SWT.RIGHT);
		lblHolderParty.setFont(SWTResourceManager.getFont("Arial", 10,
				SWT.NORMAL));
		lblHolderParty.setBounds(11, 51, 84, 15);
		lblHolderParty
				.setText("\u041E\u0440\u0433\u0430\u043D\u0438\u0437\u0430\u0446\u0438\u044F");

		chkPerson = new Button(grpHolder, SWT.CHECK);
		chkPerson.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				Party temp = carnet.getHolder();
				switchTo(chkPerson.getSelection(), lblHolderParty,
						txtHolderParty, txtDescription);
				addPartyToCarnet(chkPerson.getSelection(), true, holderParty);
				fillInPartyFields(chkPerson.getSelection(), txtHolderParty,
						txtDescription, holderParty);
				holderParty = temp;
			}
		});
		chkPerson.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		chkPerson.setBounds(21, 20, 135, 16);
		chkPerson
				.setText("\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");

		Label lblDescription = new Label(grpHolder, SWT.NONE);
		lblDescription.setFont(SWTResourceManager.getFont("Arial", 10,
				SWT.NORMAL));
		lblDescription.setAlignment(SWT.RIGHT);
		lblDescription.setBounds(21, 78, 75, 15);
		lblDescription
				.setText("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");

		txtDescription = new Label(grpHolder, SWT.BORDER | SWT.WRAP);
		// txtDescription.setEditable(false);
		txtDescription.setBounds(101, 75, 441, 62);

		textNumber = new CLabel(composite, SWT.BORDER);
		textNumber.setEnabled(false);
		textNumber.setBounds(68, 18, 504, 21);
		textNumber.setText("");

		Group grRepresenter = new Group(composite, SWT.NONE);
		grRepresenter
				.setText("\u041F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043B\u044C");
		grRepresenter.setFont(SWTResourceManager.getFont("Arial CYR", 10,
				SWT.NORMAL));
		grRepresenter.setBounds(22, 205, 552, 147);

		txtRepParty = new Text(grRepresenter, SWT.BORDER | SWT.READ_ONLY);
		txtRepParty.setBounds(101, 43, 410, 26);

		Button bnRepEdit = new Button(grRepresenter, SWT.NONE);
		bnRepEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				openPartyListForm(chkRepPerson.getSelection(), false,
						txtRepParty, txtRepDescription);
			}
		});
		bnRepEdit.setImage(SWTResourceManager.getImage(CarnetApplication.class,
				"/com/sun/java/swing/plaf/windows/icons/Directory.gif"));
		bnRepEdit.setBounds(511, 43, 31, 25);

		lblRepParty = new Label(grRepresenter, SWT.NONE);
		lblRepParty
				.setText("\u041E\u0440\u0433\u0430\u043D\u0438\u0437\u0430\u0446\u0438\u044F");
		lblRepParty
				.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		lblRepParty.setAlignment(SWT.RIGHT);
		lblRepParty.setBounds(11, 51, 84, 15);

		chkRepPerson = new Button(grRepresenter, SWT.CHECK);
		chkRepPerson.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				Party temp = carnet.getRepresenter();
				switchTo(chkRepPerson.getSelection(), lblRepParty, txtRepParty,
						txtRepDescription);
				addPartyToCarnet(chkRepPerson.getSelection(), false, repParty);
				
				fillInPartyFields(chkPerson.getSelection(), txtRepParty,
						txtRepDescription, repParty);
				repParty = temp;
			}
		});
		chkRepPerson
				.setText("\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");
		chkRepPerson.setFont(SWTResourceManager
				.getFont("Arial", 10, SWT.NORMAL));
		chkRepPerson.setBounds(21, 20, 135, 16);

		Label lblRepDescription = new Label(grRepresenter, SWT.NONE);
		lblRepDescription
				.setText("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		lblRepDescription.setFont(SWTResourceManager.getFont("Arial", 10,
				SWT.NORMAL));
		lblRepDescription.setAlignment(SWT.RIGHT);
		lblRepDescription.setBounds(21, 78, 75, 15);

		txtRepDescription = new Label(grRepresenter, SWT.BORDER | SWT.WRAP);
		txtRepDescription.setBounds(101, 75, 441, 62);

		Label label = new Label(composite, SWT.NONE);
		label.setBounds(501, 18, 92, 0);

		TabItem tabGoods = new TabItem(tabFolder, SWT.NONE);
		tabGoods.setText("\u041F\u0440\u043E\u0434\u0443\u043A\u0446\u0438\u044F");

		tblGoods = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tblGoods.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				int index = tblGoods.getSelectionIndex();
				ProductItemForm pform = new ProductItemForm(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				ProductItem product = carnet.getGoods().get(index);
				pform.setProduct(product);
				pform.open();

				if (product.isModified()) {
					TableItem item = tblGoods.getItem(index);
					item.setText(0, product.getFirstNumber());
					item.setText(1, product.getDescription());
					item.setText(2, "" + product.getCount());
					item.setText(3, "" + product.getWeight());
					item.setText(4, "" + product.getValue());
					item.setText(5,
							"" + product.getCountry() != null ? (product
									.getCountry().getCode() != null ? product
									.getCountry().getCode() : "") : "");
					product.setModified(false);
				}
			}
		});
		tabGoods.setControl(tblGoods);
		tblGoods.setHeaderVisible(true);
		tblGoods.setLinesVisible(true);

		TabItem tabPdf = new TabItem(tabFolder, SWT.NONE);
		tabPdf.setText("\u041A\u0430\u0440\u043D\u0435\u0442");
		browser = new Browser(tabFolder, SWT.BORDER);
		tabPdf.setControl(browser);
		
				TabItem tabClaim = new TabItem(tabFolder, SWT.NONE);
				tabClaim.setText("\u041F\u0440\u0435\u0442\u0435\u043D\u0437\u0438\u0438");

		TabItem tabFInansial = new TabItem(tabFolder, SWT.NONE);
		tabFInansial.setText("\u0414\u043E\u0433\u043E\u0432\u043E\u0440");

		TabItem tabWord = new TabItem(tabFolder, SWT.NONE);
		tabWord.setText("\u0414\u043E\u043A\u0443\u043C\u0435\u043D\u0442\u044B");
		frame = new OleFrame(tabFolder, SWT.NONE);
		tabWord.setControl(frame);
		frame.setLayout(null);
		clientSite = new OleClientSite(frame, SWT.NONE, "Word.Document");
		clientSite.setBounds(0, 0, 280, 66);
		clientSite.setLayout(new FillLayout(SWT.HORIZONTAL));
		carnetForm.setWeights(new int[] { 140, 490 });

		// create popup menu
		Menu popupMenu = new Menu(listCarnet);
		MenuItem newItem = new MenuItem(popupMenu, SWT.CASCADE);
		newItem.setText("Новый");

		MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
		deleteItem.setText("Удалить");

		Menu newMenu = new Menu(popupMenu);
		newItem.setMenu(newMenu);

		MenuItem shortcutItem = new MenuItem(newMenu, SWT.NONE);
		shortcutItem.setText("Карнет");

		listCarnet.setMenu(popupMenu);
		stackLayout.topControl = carnetForm;

		/*
		mnCompanyRepository.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Load Company Repository");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xml", "*.*" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				LOG.debug(selected);

				if (selected != null && !selected.isEmpty()) {
					CompanyRepositoryLoader.getInstance(selected).load();
					CompanyRepository.getInstance().sort(false);
					switchTo(chkPerson.getSelection(), lblHolderParty,
							txtHolderParty, txtDescription);
					switchTo(chkRepPerson.getSelection(), lblRepParty,
							txtRepParty, txtRepDescription);
				}
			}
		});
		*/

		/*
		mnPersonRepository.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Load Person Repository");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xml", "*.*" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				LOG.debug(selected);

				if (selected != null && !selected.isEmpty()) {
					PersonRepositoryLoader.getInstance(selected).load();
					PersonRepository.getInstance().sort(false);
					switchTo(chkPerson.getSelection(), lblHolderParty,
							txtHolderParty, txtDescription);
					switchTo(chkRepPerson.getSelection(), lblRepParty,
							txtRepParty, txtRepDescription);
				}
			}
		});
		*/
		
		fillInCurrencyList(new String[] { "USD", "EUR", "BYR" });
		fillInTargetsList(cbTarget);
		fillInType(cbType);
		
	}

	private void fillInType(CCombo cb) {
		cb.removeAll();
		for (ProductType type : ProductTypeList.getInstance()) {
			cb.add(type.getName() != null ? type.getName() : "");
		}
	}

	private void fillInTargetsList(CCombo cb) {
		cb.removeAll();
		for (Target type : TargetList.getInstance()) {
			cb.add(type.getName() != null ? type.getName() : "");
		}
	}
	
	protected void openPartyListForm(boolean isPerson, boolean isHolder,
			Text name, Label desc) {
		PartyListForm pform = new PartyListForm(shell, SWT.ICON_INFORMATION
				| SWT.OK);
		pform.setPerson(isPerson);
		pform.setParty(isHolder ? carnet.getHolder() : carnet.getRepresenter());
		pform.open();

		if (pform.isSelected()) {
			Party party = pform.getParty();
			addPartyToCarnet(isPerson, isHolder, party);

			fillInPartyFields(isPerson, name, desc, party);
		}
	}

	private void fillInPartyFields(boolean isPerson, Text name, Label desc,
			Party party) {
		if (party != null) {
			name.setText(party.getName());
			if (isPerson) {
				if (party != null) {
					Passport passport = ((Person) party).getPassport();
					desc.setText(party.getName() + "  Паспорт "
							+ (passport != null ? passport.toString() : ""));
				}

			} else {
				if (party != null) {
					com.belcci.carnet.model.Address address = ((Company) party)
							.getAddresses().get(0);
					desc.setText(party.getName() + " " + address.toString());
				}
			}
		}
	}

	protected void addPartyToCarnet(boolean isPerson, boolean isHolder,
			Party party) {

		if (isPerson) {
			Person person = party == null ? null : (Person) party;

			if (isHolder) {
				carnet.setHolderPerson(true);
				carnet.setHolder(person);
			} else {
				carnet.setRepresenterPerson(true);
				carnet.setRepresenter(person);
			}
		} else {

			Company company = party == null ? null : (Company) party;

			if (isHolder) {
				carnet.setHolderPerson(false);
				carnet.setHolder(company);
			} else {
				carnet.setRepresenterPerson(false);
				carnet.setRepresenter(company);
			}
		}

	}

	protected void switchTo(boolean selection, Label lbl, Text txt,
			Label description) {
		if (selection) {
			if (lbl != null)
				lbl.setText("ФИО");
		} else {
			if (lbl != null)
				lbl.setText("Организация");
		}
		txt.setText("");
		description.setText("");
	}

	protected void fillInCombo(CCombo cb, java.util.List<Party> list) {
		cb.removeAll();
		for (Party element : list) {
			cb.add(element.getName() != null ? element.getName() : "");
		}
	}

	class Open implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			FileDialog fd = new FileDialog(shell, SWT.OPEN);
			fd.setText("Open");
			fd.setFilterPath("C:/");
			String[] filterExt = { "*.docx", "*.doc", "*.*" };
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			LOG.debug(selected);

			if (selected != null && !selected.isEmpty()) {
				if (carnet == null) {
					CarnetATA ncarnet = newCarnet();
					listCarnet.add(ncarnet.getNumber().toString());
					currentNumber = listCarnet.getItemCount() - 1;
					listCarnet.select(currentNumber);
					carnets.add(ncarnet);
					carnet = ncarnet;
					composite.setEnabled(true);
					loadCarnetProperies();
				}
				carnetATALoad(selected);
				fillInGoodsTable(carnet);
				// clientSite.dispose();
				//
				// clientSite = new OleClientSite(frame, SWT.NONE,
				// "Word.Document", new File(selected));
				// clientSite.doVerb(OLE.OLEIVERB_);
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}
	}

	class LoadConfig implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			FileDialog fd = new FileDialog(shell, SWT.OPEN);
			fd.setText("Load Carnet Configuration");
			fd.setFilterPath("C:/");
			String[] filterExt = { "*.xml", "*.*" };
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			LOG.debug(selected);

			if (selected != null && !selected.isEmpty()) {
				carnetConfigFileName = selected;
				loadPageConfig(carnetConfigFileName);
			}
		}

		private String readConfigFile(String carnetConfigFileName) {

			StringBuffer text = new StringBuffer();
			Reader reader = null;

			try {
				reader = new FileReader(carnetConfigFileName);
				char[] buffer = new char[1024];
				while (reader.read(buffer) > 0) {
					text.append(buffer);
				}

			} catch (Exception ex) {

			} finally {
				try {
					reader.close();
				} catch (Exception ex) {
				}
			}
			return text.toString();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}

	}

	class Save implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			FileDialog fd = new FileDialog(shell, SWT.SAVE);
			fd.setText("Save");
			fd.setFilterPath("C:/");
			String[] filterExt = { "*.pdf" };
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			CarnetPDFBuilder maker = new CarnetPDFBuilder();

			if (carnet != null) {
				try {
					maker.createPdf(selected, carnet, carnetConfigFileName);
					carnet.setCarnetFileName(selected);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				LOG.debug(selected);
				if (browser != null) {
					browser.setUrl(selected);
				}
			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	private void carnetATALoad(String fileName) {
		long start = System.currentTimeMillis();
		CarnetReader wreader = new WordReader();
		wreader.loadCarnetATA(carnet, fileName);
		LOG.debug("Duration: " + (System.currentTimeMillis() - start));
		wreader = null;
	}

	public void loadPageConfig(String carnetConfigFileName) {
		ConfigReader xreader = XMLConfigReader
				.getInstance(carnetConfigFileName);
		xreader.getCountryList();
		xreader.getJobList();
		xreader.getTargetList();
		xreader.getProductTypeList();
		CountryList.getInstance().sort(false);
	}


	public void fillInCurrencyList(String[] clist) {
		cbCurrency.removeAll();
		for (String str : clist) {
			cbCurrency.add(str);
		}
	}

	private void fillInGoodsTable(CarnetATA carnet) {
		boolean needsetvisible = false;

		if (tblGoods.isVisible()) {
			LOG.debug("Goods are not visible");
			tblGoods.setVisible(false);
			needsetvisible = true;
		}

		if (tblGoods.getItems().length > 0) {
			tblGoods.removeAll();
		}

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		tblGoods.setLayoutData(data);
		tblGoods.setLinesVisible(true);
		tblGoods.setHeaderVisible(true);

		String[] titles = { "Позиция", "Описание", "Количество", "Вес/Объем",
				"Стоимость", "Страна" };

		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(tblGoods, SWT.NONE);
			column.setText(titles[i]);
		}

		CarnetIterator iterator = carnet.getIterator();
		iterator.reset();

		while (iterator.hasNext()) {
			ProductItem product = iterator.next();
			TableItem item = new TableItem(tblGoods, SWT.NONE);
			LOG.debug("Output Row : " + product.getFirstNumber());
			item.setText(0, product.getFirstNumber());
			item.setText(1, product.getDescription());
			item.setText(2, "" + product.getCount());
			item.setText(3, "" + product.getWeight());
			item.setText(4, "" + product.getValue());
			item.setText(5, ""
					+ (product.getCountry() != null ? product.getCountry()
							.getCode() : ""));
		}

		for (int i = 0; i < titles.length; i++) {
			tblGoods.getColumn(i).pack();
		}

		if (needsetvisible) {
			tblGoods.setVisible(true);
			LOG.debug("Goods are  visible");
		}
	}

	private void fillInCarnetReportTable(java.util.List<CarnetATA> lcarnets) {
		boolean needsetvisible = false;

		if (tblReport.isVisible()) {
			tblReport.setVisible(false);
			needsetvisible = true;
		}

		if (tblReport.getItems().length > 0) {
			tblReport.removeAll();
		}

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		tblReport.setLayoutData(data);
		tblReport.setLinesVisible(true);
		tblReport.setHeaderVisible(true);

		String[] titles = { "Номер", "Дата выдачи", "Держатель",
				"Кол-во выездов", "Страны назначения", "Страны транзита",
				"Цель вывоза", "Вид продукции", "Стоимость в EUR",
				"Стоимость в USD", "Стоимость в BYR", "Эквивалент в BYR",
				"Стоимость услуги", "Доп. листы", "Возврат" };

		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(tblReport, SWT.NONE);
			column.setText(titles[i]);
		}

		for (CarnetATA wcar : lcarnets) {
			TableItem item = new TableItem(tblReport, SWT.NONE);
			item.setText(0, wcar.getNumber().toString());
			item.setText(1, "");
			item.setText(2, wcar.getHolder() != null
					&& wcar.getHolder().getName() != null ? wcar.getHolder()
					.getName() : "");
			item.setText(3, "" + wcar.getReImportNumber());
			item.setText(4, "");
			item.setText(5, "");
			item.setText(6, wcar.getUsing() != null ? wcar.getUsing() : "");
			item.setText(7, "");
			item.setText(8, "");
			item.setText(9, "");
			double value = wcar.getGoods().get(wcar.getGoods().size() - 1)
					.getValue();
			item.setText(10, "" + value);
			int exch = 9300;
			item.setText(11, "" + value / exch);
			item.setText(12, "");
			item.setText(13, "" + wcar.getAdditionalListNumber());
			item.setText(14, "нет");
		}

		for (int i = 0; i < titles.length; i++) {
			tblReport.getColumn(i).pack();
		}

		if (needsetvisible) {
			tblReport.setVisible(true);
			LOG.debug("Report is visible");
		}
	}

	private void validate() {
		if (carnet != null) {
			carnet.validate();
			carnet.validateCounts();
			carnet.validateValues();
			carnet.validateWeight();
		}

	}

	
	class Print implements SelectionListener {

		public void widgetSelected(SelectionEvent e) {
			PrintDialog printDialog = new PrintDialog(shell, SWT.NONE);
			printDialog.setText("Print");
			PrinterData printerData = printDialog.open();

			if (!(printerData == null)) {
				LOG.debug(printerData);
				printerselected = printerData.name;
			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	class PrintPDF implements SelectionListener {

		public void widgetSelected(SelectionEvent event) {
			FileDialog fd = new FileDialog(shell, SWT.OPEN);
			fd.setText("OpenPDF");
			fd.setFilterPath("C:/");
			String[] filterExt = { "*.pdf" };
			fd.setFilterExtensions(filterExt);
			String selected = fd.open();
			LOG.debug(selected);

			if (!selected.isEmpty()) {
				printPDFFile(selected, printerselected);
			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}

	}

	class NewCarnet implements SelectionListener {

		public void widgetSelected(SelectionEvent event) {
			CarnetATA ncarnet = newCarnet();
			listCarnet.add(ncarnet.getNumber().toString());
			currentNumber = listCarnet.getItemCount() - 1;
			listCarnet.select(currentNumber);
			carnets.add(ncarnet);
			carnet = ncarnet;
			composite.setEnabled(true);
			loadCarnetProperies();
			fillInGoodsTable(carnet);
			browser.setUrl(carnet.getCarnetFileName() == null ? "" : carnet
					.getCarnetFileName());
			browser.refresh();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}

	}

	class CarnetSelect implements SelectionListener {

		public void widgetSelected(SelectionEvent event) {
			int i = listCarnet.getSelectionIndices()[0];

			if (i != currentNumber) {
				carnet = carnets.get(i);
				loadCarnetProperies();
				fillInGoodsTable(carnet);
				browser.setUrl(carnet.getCarnetFileName() == null ? "" : carnet
						.getCarnetFileName());
				browser.refresh();
				currentNumber = i;
				composite.setEnabled(true);
			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}

	}

	private int findCompanyInList(String name, CCombo cbCompany) {
		int sel = 0;

		for (String item : cbCompany.getItems()) {
			if (name.equals(item)) {
				break;
			}
			sel++;
		}
		Logger.getLogger(CarnetApplication.class).info("Sel = " + sel);
		return sel;
	}

	private void printPDFFile(String fileName, String printername) {
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.DUPLEX);
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor,
				patts);

		if (ps.length == 0) {
			throw new IllegalStateException("No Printer found");
		}
		LOG.debug("Available printers: " + Arrays.asList(ps));

		PrintService myService = null;
		LOG.debug(printername);
		for (PrintService printService : ps) {
			if (printService.getName().equals(printername)) {
				myService = printService;
				break;
			}
		}

		if (myService == null) {
			throw new IllegalStateException("Printer not found");
		}

		try {
			FileInputStream fis = new FileInputStream(fileName);
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE,
					null);
			DocPrintJob printJob = myService.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();
		} catch (Exception ex) {
            LOG.error(ex.getMessage());    
		}

	}

	private CarnetATA newCarnet() {
		CarnetATA ncarnet = new CarnetATA();
		CarnetNumber number = new CarnetNumber();
		number.setRegion("II");
		number.setYear("13");
		number.setNumber(carnetNumber++);
		ncarnet.setNumber(number);
		return ncarnet;
	}

	private void saveCarnetProperties(CarnetATA carnet) {
		if (carnet != null && isModifiable) {
		}
	}

	private void loadCarnetProperies() {
		isModifiable = false;
		textNumber.setText(carnet.getNumber().toString());

		fillInCountryTable(tableTarget, carnet.getTargetCountries(),
				new String[] { "Страна", "Количество въездов/выездов" });
		fillInCountryTable(tableTransit, carnet.getTransitCountries(),
				new String[] { "Страна", "Количество транзитов" });

		chkRepPerson.setSelection(carnet.getRepresenter() != null ? carnet.isRepresenterPerson() : false);
		
		switchTo(chkRepPerson.getSelection(), lblRepParty, txtRepParty,
				txtRepDescription);
		fillInPartyFields(chkRepPerson.getSelection(), txtRepParty,
				txtRepDescription, carnet.getRepresenter());
		repParty = null;

		chkPerson.setSelection(carnet.getHolder() != null ? carnet.isHolderPerson() : false);
		switchTo(chkPerson.getSelection(), lblHolderParty, txtHolderParty,
				txtDescription);
		fillInPartyFields(chkPerson.getSelection(), txtHolderParty,
				txtDescription, carnet.getHolder());
        holderParty = null;     

		if (carnet.getDtExit() != null) {
			dtExit.setDate(carnet.getDtExit().get(Calendar.YEAR), carnet
					.getDtExit().get(Calendar.MONTH),
					carnet.getDtExit().get(Calendar.DAY_OF_MONTH));
		}
		if (carnet.getDtStart() != null) {
			dtStart.setDate(carnet.getDtStart().get(Calendar.YEAR), carnet
					.getDtStart().get(Calendar.MONTH),
					carnet.getDtStart().get(Calendar.DAY_OF_MONTH));
		}
		if (carnet.getDtEnd() != null) {
			dtEnd.setDate(carnet.getDtEnd().get(Calendar.YEAR), carnet
					.getDtEnd().get(Calendar.MONTH),
					carnet.getDtEnd().get(Calendar.DAY_OF_MONTH));
		}
		if (carnet.getDtValid() != null) {
			dtValid.setDate(carnet.getDtValid().get(Calendar.YEAR), carnet
					.getDtValid().get(Calendar.MONTH),
					carnet.getDtValid().get(Calendar.DAY_OF_MONTH));
		}
		if (carnet.getDtReturn() != null) {
			dtReturn.setDate(carnet.getDtReturn().get(Calendar.YEAR), carnet
					.getDtReturn().get(Calendar.MONTH), carnet.getDtReturn()
					.get(Calendar.DAY_OF_MONTH));
		}
		loadValue();
		selectTarget(carnet.getTarget());
		selectType(carnet.getProductType());

		chkReturn.setSelection(carnet.isReturned());
		chkIssued.setSelection(carnet.isIssued());
		isModifiable = true;
	}

	private void loadValue() {
		selectCurrency(carnet.getGoodsTotal().getCurrency());
		txtValue.setText("" + carnet.getGoodsTotal().getValue());
		txtFee.setText("" + carnet.getGoodsTotal().getFee());
	}

	private void selectCurrency(Currency currency) {
		cbCurrency.select(-1);
		cbCurrency.clearSelection();

		if (currency != null) {
			for (int i = 0; i < cbCurrency.getItemCount(); i++) {
				if (cbCurrency.getItem(i).equals(currency.getCurrencyCode())) {
					cbCurrency.select(i);
					break;
				}
			}
		}
	}

	private void selectType(ProductType product) {
		cbType.select(-1);
		cbType.clearSelection();

		if (product != null) {

			int sel = 0;
			for (String item : cbType.getItems()) {
				if (item.equals(product.getName())) {
					LOG.debug("selectType: "
							+ cbType.getSelectionIndex() + " "
							+ cbType.getText() + "Carnet.Type: "
							+ product.getName());
					cbType.select(sel);
					LOG.debug("selectType: "
							+ cbType.getSelectionIndex() + " "
							+ cbType.getText() + "Carnet.Type: "
							+ product.getName());
					break;
				}
				sel++;
			}
		}
	}

	private void selectTarget(Target target) {
		cbTarget.select(-1);
		cbTarget.clearSelection();

		if (target != null) {
			int sel = 0;
			for (String item : cbTarget.getItems()) {
				if (item.equals(target.getName())) {
					LOG.debug("selectTarget: "
							+ cbTarget.getSelectionIndex() + " "
							+ cbTarget.getText() + " Carnet.target: "
							+ target.getName());
					cbTarget.select(sel);
					LOG.debug("selectTarget: "
							+ cbTarget.getSelectionIndex() + " "
							+ cbTarget.getText() + " Carnet.target: "
							+ target.getName());
					break;
				}
				sel++;
			}
		}
	}

	private int findSelectionByName(CCombo ccc, String name) {
		int sel = -1;

		for (int i = 0; i < ccc.getItems().length; i++) {
			if (ccc.getItem(i).equals(name)) {
				sel = i;
				break;
			}
		}
		return sel;
	}

	private void msgBox(String title, String message) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION
				| SWT.OK);
		messageBox.setMessage(message);
		messageBox.setText(title);
		int response = messageBox.open();
	}

	private void delCountryFromTable(Table table) {
		if (table.getSelectionIndex() > -1) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
					| SWT.YES | SWT.NO);
			messageBox.setMessage("Удалять элемент из таблицы?");
			messageBox.setText("Удаление");
			int response = messageBox.open();

			if (response == SWT.YES) {
				int sel = table.getSelectionIndex();
				table.remove(sel);
				carnet.getTargetCountries().remove(sel);
			}
		}
	}

	private void addCountryToTable(Table table,
			java.util.List<CountryQuantity> list, String title) {
		CountryQuantityForm form = new CountryQuantityForm(shell, SWT.SHEET);
		form.setText(title);
		form.setTitle(title);
		Object response = form.open();

		if (response != null) {
			list.add(form.getQcountry());
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, form.getQcountry().getCountry().getName());
			item.setText(1, "" + form.getQcountry().getQuantity());
		}
	}

	private void editCountryToTable(Table table,
			java.util.List<CountryQuantity> list, String title) {
		if (table.getSelectionIndex() > -1) {
			CountryQuantityForm form = new CountryQuantityForm(shell,
					SWT.DIALOG_TRIM);
			form.setTitle(title);
			form.setText(title);
			form.setQcountry(list.get(table.getSelectionIndex()));
			Object response = form.open();

			if (response != null) {
				TableItem item = table.getItem(table.getSelectionIndex());
				item.setText(0, form.getQcountry().getCountry().getName());
				item.setText(1, "" + form.getQcountry().getQuantity());
			}
		}
	}

	private void fillInCountryTable(Table table,
			java.util.List<CountryQuantity> list, String[] titles) {

		if (table.getItems().length > 0) {
			table.removeAll();
		}

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, list.get(i).getCountry().getName());
			item.setText(1, "" + list.get(i).getQuantity());
		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}

		LOG.debug(table.getColumn(0).getWidth());
		table.getColumn(0).setWidth((int) (table.getSize().x * 0.6));
		table.getColumn(1).setWidth(
				table.getSize().x - table.getColumn(0).getWidth() - 4);
		LOG.debug(table.getColumn(0).getWidth());

	}
}
