package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class OrderConfirmationPage extends BaseClass{

	//@FindBy(xpath="//p/strong[contains(text(),'Your Order on My Store is complete')]")
    
	@FindBy(xpath="//p[contains(text(),'Your order on My Shop is complete')]")
	WebElement confirmMessage;
	
	public OrderConfirmationPage() {
		PageFactory.initElements(getDriver(),this);
	}
	
	public String validateConfirmMessage() {
		String confirmMsg = confirmMessage.getText();
		return confirmMsg;
		
	}
}
