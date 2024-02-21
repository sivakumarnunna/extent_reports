package com.example.definitions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.example.utils.ConfigReader;
import com.example.utils.EmailSender;
import com.example.utils.HelperClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
 
public class Hooks {
         
   static int totalScearios=0;
  static int passed=0;
   static int failed=0;
 static   int skipped=0;
 public static Properties prop =null;
 
public static Map<String,String> result = new LinkedHashMap<String,String>();
 
 
 @BeforeAll
 public static void before_all() throws IOException {
	 
	 ConfigReader.readProperties();
	 
	com.example.utils.FileUtils.deleteFiles(new File(System.getProperty("user.dir")),"ExtentReports");
    
 }
 
    @After
    public static void tearDown(Scenario scenario) {
    	
    	
    	result.put(scenario.getName(), scenario.getStatus().toString());
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
    public static void email() throws FileNotFoundException, IOException {
    	
    	ConfigReader.setProperty("totalScenarios", Integer.toString(totalScearios));
    	ConfigReader.setProperty("passed", Integer.toString(passed));
    	ConfigReader.setProperty("failed", Integer.toString(failed));
    	ConfigReader.setProperty("skipped", Integer.toString(skipped));
    	
    }
     
    
}