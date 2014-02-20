package com.belcci.carnet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class CarnetATA {
	private long id;
	private List<ProductItem> goods = 
			new ArrayList<ProductItem>();
	private CarnetNumber number;
	
	private boolean isHolderPerson = false;
	private boolean isRepresenterPerson = false;
	private Party holder;
	private Party representer;
	private Employer director;	
	private Employer responser;
	private Employer reciever;
		
	private String using;
	private String eusing;
	private int reExportNumber;
	private int reImportNumber;
	private int transitNumber;

	private Calendar dtExit = 
			new GregorianCalendar();
	private Calendar dtStart = 
			new GregorianCalendar();
	private Calendar dtEnd = 
			new GregorianCalendar();	
	private Calendar dtValid = 
			new GregorianCalendar();;
	private Calendar dtReturn = 
			new GregorianCalendar();
	private Calendar dtIssued = 
			new GregorianCalendar();
	
	private List<CountryQuantity> targetCountries = 
			new ArrayList<CountryQuantity>();
	private List<CountryQuantity> transitCountries = 
			new ArrayList<CountryQuantity>();

	private boolean isReturned = false;
	private boolean isIssued = false;
	private ProductType productType;
	private Target target;
	
	private double valueService;
	private int voucherNumber;
	private CarnetIterator iterator;
	private CarnetTotal total;
	private CarnetTotal goodsTotal;
	private int additionalListNumber = 0;	
	private int cursor;
	private String carnetFileName;
	
	
	public Calendar getDtIssued() {
		return dtIssued;
	}
	
	public void setDtIssued(Calendar dtIssued) {
		this.dtIssued = dtIssued;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}
	
	public boolean isHolderPerson() {
		return isHolderPerson;
	}

	public void setHolderPerson(boolean isHolderPerson) {
		this.isHolderPerson = isHolderPerson;
	}

	public boolean isRepresenterPerson() {
		return isRepresenterPerson;
	}

	public void setRepresenterPerson(boolean isRepresenterPerson) {
		this.isRepresenterPerson = isRepresenterPerson;
	}

	public Calendar getDtStart() {
		return dtStart;
	}

	public void setDtStart(int year, int month, int day) {
		dtStart.set(Calendar.YEAR, year);
		dtStart.set(Calendar.MONTH, month);
		dtStart.set(Calendar.DAY_OF_MONTH, day);
	}

	public void setDtExit(int year, int month, int day) {
		dtExit.set(Calendar.YEAR, year);
		dtExit.set(Calendar.MONTH, month);
		dtExit.set(Calendar.DAY_OF_MONTH, day);
	}

	
	public Calendar getDtValid() {
		return dtValid;
	}

	public void setDtValid(int year, int month, int day) {
		dtValid.set(Calendar.YEAR, year);
		dtValid.set(Calendar.MONTH, month);
		dtValid.set(Calendar.DAY_OF_MONTH, day);
	}

	public Calendar getDtReturn() {
		return dtReturn;
	}

	public void setDtReturn(int year, int month, int day) {
		dtReturn.set(Calendar.YEAR, year);
		dtReturn.set(Calendar.MONTH, month);
		dtReturn.set(Calendar.DAY_OF_MONTH, day);
	}

	public int getReExportNumber() {
		return reExportNumber;
	}

	public void setReExportNumber(int exportNumber) {
		reExportNumber = exportNumber;
	}

	public int getReImportNumber() {
		return reImportNumber;
	}

	public void setReImportNumber(int importNumber) {
		reImportNumber = importNumber;
	}

	public int getTransitNumber() {
		return transitNumber;
	}

	public void setTransitNumber(int trNumber) {
		transitNumber = trNumber;
	}

	public void addProductItem(ProductItem product) {
		if (goods == null) {
			goods = new ArrayList<ProductItem>();
		}
		goods.add(product);
	}

	public List<ProductItem> getGoods() {
		return goods;
	}

	public void setGoods(List<ProductItem> goods) {
		this.goods = goods;
	}

	public CarnetNumber getNumber() {
		return number;
	}

	public void setNumber(CarnetNumber number) {
		this.number = number;
	}

	public Party getHolder() {
		return holder;
	}

	public void setHolder(Party holder) {
		this.holder = holder;
	}

	public Party getRepresenter() {
		return representer;
	}

	public String getUsing() {
		return using;
	}

	public void setUsing(String using) {
		this.using = using;
	}

	public void print() {
		for (ProductItem product : goods) {
			System.out.println(product);
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (ProductItem product : goods) {
			sb.append(product.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean validate() {
		return (validateValues() && validateCounts() && validateWeight() && validateCountries());
	}

	private boolean validateCountries() {
		return true;

	}

	public boolean validateCounts() {
		ProductItem product;
		int count = 0;
		int dif;
		String number;

		for (int i = 0; i < (goods.size() - 2); i++) {
			product = goods.get(i);
			number = product.getFirstNumber();
			dif = calculateDifference(number);

			if (dif != product.getCount()) {
				System.out
						.println("Ops. Count of items doesn't match number's range in row "
								+ (i + 1));
				return false;
			}
			count += product.getCount();
		}

		product = goods.get(goods.size() - 1);
		System.out.println("Count = " + count + "  Product.Count = "
				+ product.getCount());
		if (count == product.getCount()) {
			return true;
		} else {
			return false;
		}
	}

	public int calculateDifference(String number) {
		int ret = 1;
		int pos = number.indexOf("-");

		if (pos > 0) {
			ret = Integer.parseInt(number.substring(0, pos - 1))
					- Integer.parseInt(number.substring(pos + 1));
		}
		return ret;
	}

	public boolean validateValues() {
		ProductItem product;
		int value = 0;

		for (int i = 0; i < (goods.size() - 2); i++) {
			product = goods.get(i);
			value += product.getValue();
		}

		product = goods.get(goods.size() - 1);
		System.out.println("Value = " + value + "  Product.Value = "
				+ product.getValue());
		if (value == product.getValue()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateWeight() {
		ProductItem product;
		double weight = 0;

		for (int i = 0; i < (goods.size() - 2); i++) {
			product = goods.get(i);
			weight += product.getWeight();
		}

		product = goods.get(goods.size() - 1);
		System.out.println("Weight = " + weight + "  Product.Weight = "
				+ product.getWeight());

		if (weight == product.getWeight()) {
			return true;
		} else {
			return false;
		}
	}

	public CarnetTotal getTotal() {
		if (total == null) {
			total = new CarnetTotal();
		}
		return total;
	}

	public void setTotal(CarnetTotal total) {
		this.total = total;
	}

	public CarnetTotal getGoodsTotal() {
		if (goodsTotal == null) {
			goodsTotal = new CarnetTotal();
		}
		return goodsTotal;
	}

	public void setGoodsTotal(CarnetTotal c) {
		this.goodsTotal = goodsTotal;
	}
	
	public CarnetIterator getIterator() {
		if (iterator == null) {
			iterator = new CarnetIteratorImpl();
		}
		return iterator;
	}

	public class CarnetIteratorImpl implements CarnetIterator {

		public CarnetIteratorImpl() {
			cursor = -1;
		}

		public ProductItem first() {
			ProductItem product = null;
			if (goods != null && goods.size() > 0) {
				cursor = 0;
				product = goods.get(cursor);
				emptyTotal();
			}
			return product;
		}

		public boolean hasNext() {
			return ((goods != null) && (goods.size() > 0) && (cursor < goods
					.size() - 1));
		}

		public ProductItem next() {
			ProductItem product = null;
			if (goods != null && goods.size() > 0
					&& (cursor < goods.size() - 1)) {
				cursor++;
				product = goods.get(cursor);
			}
			return product;
		}

		public ProductItem prev() {
			ProductItem product = null;
			if (goods != null && goods.size() > 0 && (cursor > 0)) {
				cursor--;
				product = goods.get(cursor);
			}
			return product;
		}

		@Override
		public void reset() {
			cursor = -1;
			emptyTotal();
			additionalListNumber = 0;
			voucherNumber = 0;
		}

	}

	public int getAdditionalListNumber() {
		return additionalListNumber;
	}

	public void emptyTotal() {
		if (total != null) {
			total.empty();
		}
	}

	public void setAdditionalListNumber(int addListNumber) {
		additionalListNumber = addListNumber;
	}

	public int getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(int voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public void emptyGoods() {
		goods = null;
	}

	public Employer getDirector() {
		return director;
	}


	public String getCarnetFileName() {
		return carnetFileName;
	}

	public void setCarnetFileName(String carnetFileName) {
		this.carnetFileName = carnetFileName;
	}

	
	public List<CountryQuantity> getTransitCountries() {
		return transitCountries;
	}

	public void setTransitCountries(List<CountryQuantity> transitCountries) {
		this.transitCountries = transitCountries;
	}

	public double getValueService() {
		return valueService;
	}

	public void setValueService(double valueService) {
		this.valueService = valueService;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public ProductType getProType() {
		return productType;
	}

	public void setProType(ProductType proType) {
		this.productType = proType;
	}

	public Employer getResponser() {
		return responser;
	}

	public void setResponser(Employer responser) {
		this.responser = responser;
	}

	public Employer getReciever() {
		return reciever;
	}

	public void setReciever(Employer reciever) {
		this.reciever = reciever;
	}

	public Calendar getDtExit() {
		return dtExit;
	}

	public void setDtExit(Calendar dtExit) {
		this.dtExit = dtExit;
	}

	public Calendar getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(int year, int month, int day) {
		dtEnd.set(Calendar.YEAR, year);
		dtEnd.set(Calendar.MONTH, month);
		dtEnd.set(Calendar.DAY_OF_MONTH, day);
	}

	public List<CountryQuantity> getTargetCountries() {
		return targetCountries;
	}

	public void setTargetCountries(List<CountryQuantity> targetCountries) {
		this.targetCountries = targetCountries;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public void setRepresenter(Party representer) {
		this.representer = representer;
	}

	public void setDirector(Employer director) {
		this.director = director;
	}

	public void setDtStart(Calendar dtStart) {
		this.dtStart = dtStart;
	}

	public void setDtValid(Calendar dtValid) {
		this.dtValid = dtValid;
	}

	public void setDtReturn(Calendar dtReturn) {
		this.dtReturn = dtReturn;
	}
	
	public String getEusing() {
		return eusing;
	}

	public void setEusing(String eusing) {
		this.eusing = eusing;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}


}
