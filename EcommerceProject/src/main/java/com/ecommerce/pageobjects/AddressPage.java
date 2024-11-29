package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class AddressPage extends BaseClass{

	@FindBy(xpath="//span[text()='Proceed to checkout']")
	WebElement proceedToCheckOut;
	
	
	public AddressPage() {
		PageFactory.initElements(getDriver(),this);
	}
	
	public ShippingPage clickOnCheckOut() throws Throwable {
		Action.click(getDriver(),proceedToCheckOut);
		return new ShippingPage();
	}
}
