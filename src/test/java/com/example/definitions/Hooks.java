package com.example.definitions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.example.utils.EmailSender;
import com.example.utils.HelperClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import java.util.Map.Entry;
 
public class Hooks {
         
   static int totalScearios=0;
  static int passed=0;
   static int failed=0;
 static   int skipped=0;
 public static Properties prop =null;
 
 @BeforeAll
 public static void configReader() throws IOException {
	 
     prop = new Properties();
	 prop.load(new FileInputStream("src/test/resources/config.properties"));
	 
	Set<Entry<Object,Object>> propertiesset =  prop.entrySet();
	   System.out.println(propertiesset);

	   for(Entry<Object,Object> entry : propertiesset) {
		   System.out.println(entry.getValue());
		  String env =   System.getenv(entry.getKey().toString());
		  
		  if(env!=null && env.trim().isEmpty()) {
				 prop.setProperty(entry.getKey().toString(), env);
			}
		   
	   }
	   
	   System.out.println(prop.getProperty("email-receivers"));
	
 }
 
    @After
    public static void tearDown(Scenario scenario) {
    	totalScearios=totalScearios+1;
         
    	io.cucumber.java.Status status =scenario.getStatus();
    	if(status.name().equalsIgnoreCase("passed")){
    		passed=passed+1;
    	}
    	if(status.name().equalsIgnoreCase("skipped")){
    		skipped=skipped+1;
    	}
    	
        //validate if scenario has failed
        if(scenario.isFailed()) {
        	failed=failed+1;
            final byte[] screenshot = ((TakesScreenshot) HelperClass.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName()); 
        }   
     
        HelperClass.tearDown();
    }
    
    @AfterAll
    public static void email() {
    	
		String htmlContent = "<html><body>  <h3>Execution Summary</h3>\n"
				+ "<table border='1'><tr>" + "<th>Total Testcases</th>" + "<th>passed</th>"
				+ "<th>Failed</th>" + "<th>skipped</th>" + "<tr>" + "<td>" + totalScearios + "</td>" + "<td>" + passed
				+ "</td>" + "<td>" + failed + "</td>" + "<td>" + skipped + "</td>" + "</tr>" + "</table>	  <h4>Please find attached test report for more details</h4>\n"
						+ "</body></html>";
		EmailSender.sendEmail(prop.getProperty("email-receivers"), "Automation status", htmlContent,"ExtentReports/SparkReport 19_Feb_24 10_25_45/PdfReport/ExtentPdf.pdf");
    	
    }
    
}