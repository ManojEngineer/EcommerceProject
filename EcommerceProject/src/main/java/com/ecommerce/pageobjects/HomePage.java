package com.ecommerce.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class HomePage extends BaseClass {

	
	@FindBy(xpath="//span[text()='My wishlist']")
	WebElement myWishList;
	
	@FindBy(xpath= "//span[text()='Order history and details']")
	WebElement orderHistory;
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateMyWishList() throws Throwable {
		return Action.isDisplayed(getDriver(),myWishList);
	}
	
	public boolean validateOrderHistory() throws Throwable{
		return Action.isDisplayed(getDriver(), orderHistory);
	}
	
	public String getCurrURL() {
		String homePageURL = getDriver().getCurrentUrl();
		return homePageURL;
	}
	
	
}
