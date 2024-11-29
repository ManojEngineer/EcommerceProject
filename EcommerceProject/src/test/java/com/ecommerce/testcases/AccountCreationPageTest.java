package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.AccountCreationPage;
import com.ecommerce.pageobjects.IndexPage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.utility.Log;

public class AccountCreationPageTest extends BaseClass {

	LoginPage loginPage;
	IndexPage indexPage;
	AccountCreationPage accountCreationPage;
	
	@Parameters("browser")
	@BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}
	
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	
	@Test(groups="Sanity",dataProvider = "email", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest(String email) throws Throwable{
		Log.startTestCase("createAccountTest");
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
        accountCreationPage = loginPage.createNewAccount(email);
		boolean result = accountCreationPage.validateAccountCreatePage();
		Assert.assertTrue(result);
		Log.endTestCase("verifyCreateAccountPageTest");
	}
	
	
}
