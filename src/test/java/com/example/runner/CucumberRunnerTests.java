package com.example.runner;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.example.utils.HelperClass;

import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
   
@CucumberOptions(tags = "", features = "src/test/resources/features/LoginPage.feature", glue = "com.example.definitions",
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})


public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
	
	
	 @BeforeMethod
	    @Parameters({"browser"})
	    public static void setUp(String browser) {
		 
		 System.out.println("Browser is "+browser);
	       HelperClass.setUpDriver(browser);
	    }

}