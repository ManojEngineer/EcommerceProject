/**
 * 
 */
package com.ecommerce.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;
import com.ecommerce.dataprovider.DataProviders;
import com.ecommerce.pageobjects.AddToCartPage;
import com.ecommerce.pageobjects.AddressPage;
import com.ecommerce.pageobjects.IndexPage;
import com.ecommerce.pageobjects.LoginPage;
import com.ecommerce.pageobjects.OrderConfirmationPage;
import com.ecommerce.pageobjects.OrderPage;
import com.ecommerce.pageobjects.OrderSummaryPage;
import com.ecommerce.pageobjects.PaymentPage;
import com.ecommerce.pageobjects.SearchResultPage;
import com.ecommerce.pageobjects.ShippingPage;
import com.ecommerce.utility.Log;

/**
 * @author Manoj
 */
public class EndToEndTest extends BaseClass{

	    IndexPage index;
	    SearchResultPage searchResultPage;
	    AddToCartPage addToCartPage;
	    OrderPage orderPage;
	    LoginPage loginPage;
	    AddressPage addressPage;
	    ShippingPage shippingPage;
	    PaymentPage paymentPage;
	    OrderSummaryPage orderSummaryPage;
	    OrderConfirmationPage orderConfirmationPage;
	    
	    @Parameters("browser")	    
	    @BeforeMethod(groups={"Smoke","Sanity","Regression"})
		public void setup(String browser) {
			launchApp(browser);
		}
	
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		//getDriver().quit();
	}
	
	@Test(groups = "Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void endToEndTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("endToEndTest");
		
		index = new IndexPage();
		
		
		index = new IndexPage();
		searchResultPage = index.searchProduct(productName);
		
		
		addToCartPage = searchResultPage.clickOnProduct();
	   // Action.webDriverWait(driver, 5);
		
		
		addToCartPage.selectSize(size);
		//addToCartPage.enterQuantity("2");
		
		Action.scrollScreenByCoordinate(getDriver(),0,300);
		//Thread.sleep(30000);
		addToCartPage.clickOnAddToCart();
		Action.scrollScreenByCoordinate(getDriver(),0,400);
		
		orderPage = addToCartPage.clickOnCheckOut();
		
		Action.scrollScreenByCoordinate(getDriver(),0,400);
		loginPage = orderPage.clickOnCheckOut();
		addressPage = loginPage.login1(prop.getProperty("username"),prop.getProperty("password"));
	    shippingPage = addressPage.clickOnCheckOut();
	    
	    Thread.sleep(5000);
	    Action.scrollScreenByCoordinate(getDriver(),0,300);
	    
	    
	    Thread.sleep(5000);
	    shippingPage.checkTheTerms();
	    Thread.sleep(5000);
	    
	    Action.scrollScreenByCoordinate(getDriver(),0,300);
	    paymentPage = shippingPage.clickOnProceedToCheckOut();
	    
	    Thread.sleep(5000);
	    
	    Action.scrollScreenByCoordinate(getDriver(),0,500);
		orderSummaryPage = paymentPage.clickOnPaymentMethod();
		
		Thread.sleep(5000);
		Action.scrollScreenByCoordinate(getDriver(),0,400);
		
		Thread.sleep(5000);
        orderConfirmationPage = orderSummaryPage.clickOnconfiremOrderBtn();
        
        Action.scrollScreenByCoordinate(getDriver(),0,200);
        Thread.sleep(5000);
        String actualMessage = orderConfirmationPage.validateConfirmMessage();
	    String expectedMsg = "Your order on My Shop is complete.";
	    Assert.assertEquals(actualMessage, expectedMsg);
	    Log.endTestCase("endToEndTest");
	
	}
	
	
}
