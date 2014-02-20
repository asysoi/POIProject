package com.belcci.carnet.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


public class CarnetConfigurator {
		private final String sFileName = "carnet.properties";
		private String sDirSeparator = System.getProperty("file.separator");
		private static CarnetConfigurator configurator;

	
		public static CarnetConfigurator getInstance() {
			if (configurator == null) {
				configurator = new CarnetConfigurator();
			}
			return configurator;
		}

		public Properties readConfig() throws FileNotFoundException, IOException {
			File currentDir = new File(".");
			Properties props = new Properties();

			String sFilePath = currentDir.getCanonicalPath() + sDirSeparator
					+ sFileName;
			System.out.println(sFilePath);
			FileInputStream ins = new FileInputStream(sFilePath);
			props.load(ins);
			ins.close();

			return props;
		}

		public void storeConfig(Properties props) throws FileNotFoundException,
				IOException {
			File currentDir = new File(".");

			String sFilePath = currentDir.getCanonicalPath() + sDirSeparator
					+ sFileName;
			System.out.println(sFilePath);

			FileOutputStream out = new FileOutputStream(sFilePath);
			props.store(out, "");
			out.close();

		}

	}