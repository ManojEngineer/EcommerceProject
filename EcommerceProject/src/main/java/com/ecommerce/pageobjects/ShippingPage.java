package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class ShippingPage extends BaseClass {
	
	@FindBy(id="cgv")
	WebElement terms;
	
	//@FindBy(xpath = "//button/span[contains(text(),'Proceed to checktout')]")
	//WebElement proceedToCheckOutBtn;
	
	//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]
	
	
	//@FindBy(xpath="//span[normalize-space()='Proceed to checkout']")
	@FindBy(xpath="//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutBtn;
	
	public ShippingPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void checkTheTerms() throws Throwable {
		Action.click(getDriver(), terms);
	}
	
	public PaymentPage clickOnProceedToCheckOut() throws Throwable{
		Action.click(getDriver(), proceedToCheckOutBtn);
		return new PaymentPage();
	}
	
	

}
