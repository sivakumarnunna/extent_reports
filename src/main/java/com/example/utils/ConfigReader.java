package com.example.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

public class ConfigReader {
	
	
	
	 public static Properties properties =null;
	public static void readProperties() {
		 properties = new Properties();
		 try {
			properties.load(new FileInputStream("src/test/resources/config.properties"));
			Set<Entry<Object,Object>> propertiesset =  properties.entrySet();

			   for(Entry<Object,Object> entry : propertiesset) {
				  String env =   System.getenv(entry.getKey().toString());
				  if(env!=null && !env.isEmpty()) {
					  properties.setProperty(entry.getKey().toString(), env);
					}
			   }
			   System.out.println(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 }
	 
	 public static String getProperty(String config) throws FileNotFoundException, IOException {
	 
	
	
    return  properties.getProperty(config);
}
	 
	 public static void setProperty(String key, String value) throws FileNotFoundException, IOException {
		 
         properties.setProperty(key,value);
				
		   
	}
	 
}
