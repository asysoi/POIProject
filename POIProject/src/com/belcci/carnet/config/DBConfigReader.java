package com.belcci.carnet.config;

import java.util.List;

import com.belcci.carnet.model.CountryList;
import com.belcci.carnet.model.JobList;
import com.belcci.carnet.persistence.ProductTypeList;
import com.belcci.carnet.persistence.TargetList;


public class DBConfigReader extends ConfigReader {
    List<BoxConfig> boxes; 
    
	public List<BoxConfig> getListConfigTextBoxes(String pagename) {
         
		return null;
	}

	public List<TableConfig> getListConfigTables(String pagename) {
		return null;
	}

	@Override
	public List<Stamp> getListConfigStamps(String pagename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoxConfig> getListConfigBoxes(String pagename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoxConfig> getListConfigOutputs(String pagename) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getPropertyByName(String pagename, String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageBox> getListConfigImages(String pagename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryList getCountryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobList getJobList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetList getTargetList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductTypeList getProductTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
