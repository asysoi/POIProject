package com.belcci.carnet.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CarnetConfig {
	private Map<String, Object> pmap = new HashMap<String, Object>();
	private Properties props = new Properties();     	
    private static CarnetConfig repository;
    
    public static CarnetConfig getInstance() {
    	if (repository == null) {
    		repository = new CarnetConfig();
    	}
    	return repository;
    }
     
    public void setProperties(Properties pr) {
    	props = pr;
    }
    
    public Properties getProperties() {
    	return props;
    }
    
    public void setProperty(String key, String value) {
    	props.setProperty(key, value);
    }

    public String getProperty(String key) {
    	return  props.getProperty(key);
    }
}
