package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.HomePage;
import com.ecommerce.pageobjects.IndexPage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.utility.Log;

public class LoginPageTest extends BaseClass{
    IndexPage indexPage;
    LoginPage loginPage;
    HomePage homePage;
    
    @Parameters("browser")	
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}
	
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(dataProvider="credentials", dataProviderClass = DataProviders.class, groups = {"Smoke","Sanity"})
	public void loginTest(String uname, String passwd) throws Throwable{
		Log.startTestCase("loginTest");
		indexPage= new IndexPage();
		Log.info("user is going to click on signin");
		loginPage = indexPage.clickOnSignIn();
		Log.info("Enter Username and password");
		//homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        homePage= loginPage.login(uname,passwd);
		String actualURL = homePage.getCurrURL();
        String expectedURL = "http://www.automationpractice.pl/index.php?controller=my-account";
        Log.info("Verifying if user is able to Login");
        Assert.assertEquals(actualURL, expectedURL);
	    Log.info("Login is success");
	    Log.endTestCase("loginTest");
	}
	
	
}
