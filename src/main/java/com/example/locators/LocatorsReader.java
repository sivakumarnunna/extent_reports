package com.example.locators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorsReader {
	
	public static Properties properties;
	
	public static String getLocator(String key) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/locators.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

}
