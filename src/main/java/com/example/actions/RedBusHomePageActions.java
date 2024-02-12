package com.example.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.locators.RedBusHomePageLocators;
import com.example.utils.HelperClass;

public class RedBusHomePageActions {
	
	RedBusHomePageLocators redBusHomePageLocators = null;
	
	private WebDriver driver;
	
	public RedBusHomePageActions(WebDriver driver) {
		this.driver=driver;
		this.redBusHomePageLocators= new RedBusHomePageLocators();
        PageFactory.initElements(this.driver,redBusHomePageLocators);
	}

	
}
