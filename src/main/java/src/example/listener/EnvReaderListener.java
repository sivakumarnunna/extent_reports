package src.example.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.testng.ITestClass;

public class EnvReaderListener implements org.testng.IClassListener {
	
	Properties prop = new Properties();
	
	
    public void onBeforeClass(ITestClass cls) {
    	
    	try {
			prop.load(new FileInputStream("/Users/nunnasivakumar/git/extent_reports/src/test/resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
       Set<Entry<Object,Object>> configs =  prop.entrySet();
       
       for(Entry<Object,Object> config : configs) {
           String env = System.getenv(config.getKey().toString());
           if (env != null && ! env.trim().isEmpty()) {
        	   config.setValue(env);
        	   
        	   System.out.println(config.getKey() +"  = "+ env);
           }
       }
    	  
       Map<String, String> parameters = cls.getXmlClass().getAllParameters();

        for (Entry<String, String> parameter : parameters.entrySet()) {
        	
        	System.out.println(parameter.getKey());
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }
    }
	
 
}
