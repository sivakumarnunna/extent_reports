package com.example.runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.example.utils.ConfigReader;
import com.example.utils.EmailSender;
import com.example.utils.HelperClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
   
@CucumberOptions(tags = "", features = "src/test/resources/features/LoginPage.feature", glue = "com.example.definitions",
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})


public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
	


	   @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	
	 @BeforeMethod
	    @Parameters({"browser","place"})
	    public static void setUp(String browser,String place) throws MalformedURLException {
		 
		 System.out.println("I am in testng before method");
	       HelperClass.setUpDriver(browser,place);
	    }

	 
	  @AfterTest
		 public void publish_email() throws FileNotFoundException, IOException {
		  
			String htmlContent = "<html><body>  <h3>Execution Summary</h3>\n" + "<table border='1'><tr>"
					+ "<th>Total Testcases</th>" + "<th>passed</th>" + "<th>Failed</th>" + "<th>skipped</th>" + "<tr>"
					+ "<td>" + ConfigReader.getProperty("totalScenarios") + "</td>" + "<td>"
					+ ConfigReader.getProperty("passed") + "</td>" + "<td>" + ConfigReader.getProperty("failed")
					+ "</td>" + "<td>" + ConfigReader.getProperty("skipped") + "</td>" + "</tr>" + "</table>"
					+ "	  <h4>Please find attached test report for more details</h4>\n" + "</body></html>";
				String attachment = com.example.utils.FileUtils.getFile(new File(System.getProperty("user.dir")), ConfigReader.getProperty("report-path"));
				EmailSender.sendEmail(ConfigReader.getProperty("email-receivers"), "Automation status", htmlContent,attachment+ConfigReader.getProperty("report-file"));
		    
		 }
	    
	

}