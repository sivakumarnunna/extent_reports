package src.example.listener;

import java.util.Map;

import org.testng.ITestClass;

public class EnvReaderListener implements org.testng.IClassListener {
	
	
    public void onBeforeClass(ITestClass cls) {
        Map<String, String> parameters = cls.getXmlClass().getAllParameters();
        
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
        	
        	System.out.println(parameter.getKey());
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }
    }
	
 
}
