package com.example.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {

	private static HelperClass helperClass;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	//private static WebDriver driver;
	private static WebDriverWait wait;
	public final static int TIMEOUT = 30;
	private static String gridURL="http://192.168.0.201:4444";
	
	
	private HelperClass(String browser, String place) throws MalformedURLException {
		
		System.out.println("Browser---"+browser);
		System.out.println("place---"+place);


		if (place.equalsIgnoreCase("local")) {

			if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("inside local chrome"+place);

				ChromeOptions options = new ChromeOptions();
				//options.addArguments("--start-maximized");
				driver.set(new ChromeDriver(options));
			} else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				driver.set(new FirefoxDriver(options));
			} else if (browser.equalsIgnoreCase("edge")) {
				driver.set(new EdgeDriver());
			} else {
				System.out.println("Please set valid browser");
			}

		}
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

	}

	public static void openPage(String url) {
		driver.get().get(url);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setUpDriver(String browser,String place) throws MalformedURLException {

		if (helperClass == null) {

			helperClass = new HelperClass(browser, place);
		}
	}

	public static void tearDown() {

		if (driver != null) {
			driver.get().close();
			// driver.quit();
		}

		helperClass = null;
	}

}