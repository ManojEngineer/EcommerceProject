package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.IndexPage;
import com.ecommerce.pageobjects.SearchResultPage;
import com.ecommerce.utility.Log;

public class SearchResultPageTest extends BaseClass {
    IndexPage index;
    SearchResultPage searchResultPage;
    
	
    @Parameters("browser")	
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser);
	}
    
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups="Smoke",dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabilityTest(String productName) throws Throwable{
		Log.startTestCase("productAvailabilityTest");
		index = new IndexPage();
		searchResultPage = index.searchProduct(productName);
		boolean result = searchResultPage.isProductAvailable();
		Assert.assertTrue(result);
		Log.endTestCase("productAvailabilityTest");
		
	}
	
}
