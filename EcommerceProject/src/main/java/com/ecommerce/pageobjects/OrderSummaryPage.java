package com.ecommerce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class OrderSummaryPage extends BaseClass{

	@FindBy(xpath="//span[normalize-space()='I confirm my order']")
	WebElement confirmOderBtn;
	
	public void OrderSummary() {
		PageFactory.initElements(getDriver(),this);
	}
	
	public OrderConfirmationPage clickOnconfiremOrderBtn() {
		
		confirmOderBtn = getDriver().findElement(By.xpath("//span[normalize-space()='I confirm my order']"));
		
		Action.click(getDriver(), confirmOderBtn);
		return new OrderConfirmationPage();
	}
	
	
}
