package com.example.utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {

	private static HelperClass helperClass;

	private static WebDriver driver;
	private static WebDriverWait wait;
	public final static int TIMEOUT = 30;

	private HelperClass(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("firefox driver");
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("safari")) {
			FirefoxOptions options = new FirefoxOptions();
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please set valid browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

	}

	public static void openPage(String url) {
		driver.get(url);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setUpDriver(String browser) {

		if (helperClass == null) {

			helperClass = new HelperClass(browser);
		}
	}

	public static void tearDown() {

		if (driver != null) {
			System.out.println("closing the driver");
			driver.close();
			// driver.quit();
		}

		helperClass = null;
	}

}