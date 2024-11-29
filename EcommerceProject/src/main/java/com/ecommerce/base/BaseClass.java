package com.ecommerce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.ecommerce.actiondriver.Action;
import com.ecommerce.utility.ExtentManager;


public class BaseClass {
	public static Properties prop;
	//public static WebDriver driver;


	//Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	@BeforeSuite(groups={"Smoke","Sanity","Regression"})
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");


		try {

			prop = new Properties();
			System.out.println("super constructor invoked");
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir")+"\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("driver: "+driver);

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	public static WebDriver getDriver() {
		return driver.get();
	}


	/*
	@BeforeTest(groups={"Smoke","Sanity","Regression"})
	public void loadConfig() {

		try {

			prop = new Properties();
			System.out.println("super constructor invoked");
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir")+"\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("driver: "+driver);

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	 */


	public static void launchApp(String browserName) {
		//WebDriverManager.chromedriver().setup();
		//String browserName = prop.getProperty("browser");

		if(browserName.contains("Chrome")) {
			WebDriverManager.chromedriver().setup();

			//Set browser to ThreadLocalMap
			driver.set(new ChromeDriver());

			//driver = new ChromeDriver();
			getDriver().manage().window().maximize();
		}else if (browserName.contains("FireFox")) {
			WebDriverManager.firefoxdriver().setup();

			//Set browser to ThreadLocalMap
			driver.set(new FirefoxDriver());
			getDriver().manage().window().maximize();
			//driver = new FirefoxDriver();
		}else if (browserName.contains("Edge")) {
			WebDriverManager.edgedriver().setup();

			//Set browser to ThreadLocalMap
			driver.set(new EdgeDriver());
			getDriver().manage().window().maximize();
			//driver = new EdgeDriver();
		}


		Action.implicitWait(getDriver(), 10);
		Action.pageLoadTimeOut(getDriver(), 20);

		getDriver().get(prop.getProperty("url"));
	}


	@AfterSuite
	public void afterSuite() {
		
		ExtentManager.endReport();

	}




}
