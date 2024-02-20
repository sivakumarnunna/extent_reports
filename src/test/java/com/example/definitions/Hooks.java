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
import java.util.Map.Entry;
 
public class Hooks {
         
   static int totalScearios=0;
  static int passed=0;
   static int failed=0;
 static   int skipped=0;
 public static Properties prop =null;
 
 public static boolean deleteDirectory(File directory) {
     if (directory.isDirectory()) {
         for (File f : directory.listFiles()) {
             boolean success = deleteDirectory(f);
             if (!success) {
                 return false;
             }
         }
     }
     return directory.delete();
 }
 
 public static void deleteFiles(File directory, String prefix) {
     if (directory.isDirectory()) {
         for (File f : directory.listFiles()) {
             if (f.getName().startsWith(prefix)) {
                 deleteDirectory(f);
             }
         }
     }
 }
 public static String getFile(File directory, String regex) {
     if (directory.isDirectory()) {
         for (File f : directory.listFiles()) {
        	 System.out.println(f.getPath());
             if (f.getName().contains(regex)) {
            	 return f.getName();
             }
         }
     }
    return "no file/folder found";
 }
 
 
 @BeforeAll
 public static void configReader() throws IOException {
	 
	System.out.println("I am in before all");
	 deleteFiles(new File(System.getProperty("user.dir")),"ExtentReports");
    
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
    public static void email() throws FileNotFoundException, IOException {
    	
    	ConfigReader.setProperty("totalScenarios", Integer.toString(totalScearios));
    	ConfigReader.setProperty("passed", Integer.toString(passed));
    	ConfigReader.setProperty("failed", Integer.toString(failed));
    	ConfigReader.setProperty("skipped", Integer.toString(skipped));
    	
    	
    }
     
    
}