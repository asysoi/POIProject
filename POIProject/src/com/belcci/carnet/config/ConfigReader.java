package com.belcci.carnet.config;

import java.util.List;

import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.JobList;
import com.belcci.carnet.persistence.ProductTypeList;
import com.belcci.carnet.persistence.TargetList;

public abstract class ConfigReader {
	private CarnetPageConfig pconfig;

	public abstract List<BoxConfig> getListConfigTextBoxes(String pagename);

	public abstract List<BoxConfig> getListConfigBoxes(String pagename);

	public abstract List<TableConfig> getListConfigTables(String pagename);

	public abstract List<Stamp> getListConfigStamps(String pagename);

	public abstract List<BoxConfig> getListConfigOutputs(String pagename);
	
	public abstract List<ImageBox> getListConfigImages(String pagename);

	public abstract String getPropertyByName(String pagename, String propertyName);
	
	public abstract CountryList getCountryList();
	
	public abstract JobList getJobList();
	
    public abstract TargetList getTargetList();
	
	public abstract ProductTypeList getProductTypeList();
	
	public CarnetPageConfig getCarnetPageConfig(String pagename) {
		if (pconfig == null) {
			pconfig = new CarnetPageConfig();
		}

		// load page properties
		pconfig.setTextboxes(getListConfigTextBoxes(pagename));
		pconfig.setBoxes(getListConfigBoxes(pagename));
		pconfig.setTables(getListConfigTables(pagename));
		pconfig.setStamps(getListConfigStamps(pagename));
		pconfig.setOutputs(getListConfigOutputs(pagename));
		pconfig.setImages(getListConfigImages(pagename));

		// load page attributes
		pconfig.setName(pagename);
		pconfig.setNextPage(getPropertyByName(pagename, "nextpage"));
		pconfig.setColor(getPropertyByName(pagename, "color"));
		pconfig.setHeight(Float.parseFloat(getPropertyByName(pagename, "height")));
		pconfig.setWidth(Float.parseFloat(getPropertyByName(pagename, "width")));

		return pconfig;
	}
}
