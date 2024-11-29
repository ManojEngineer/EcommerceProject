package com.ecommerce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class AddToCartPage extends BaseClass{
	@FindBy(id="quantity_wanted")
	WebElement quantity;
	
	@FindBy(id="group_1")
	WebElement size;
	
	@FindBy(xpath="//span[text()='Add to Cart']")
	WebElement addToCartBtn;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]//h2/i")
	WebElement addToCartMessage;
	
	@FindBy(xpath="//span[normalize-space()='Proceed to checkout']")
	WebElement proceedToCheckOutBtn;
	
	
	
	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterQuantity(String quantity1)throws Throwable {
		Action.type(quantity, quantity1);
	}
	 
	public void selectSize(String size1) throws Throwable{
		Thread.sleep(5000);
		Action.selectByVisibleText(size, size1);
	}
	
	public void clickOnAddToCart() throws Throwable {
		Thread.sleep(5000);
		
		//addToCartBtn = driver.findElement(By.xpath("//span[text()='Add to Cart']"));
		
		addToCartBtn = getDriver().findElement(By.xpath("//span[normalize-space()='Add to cart']"));
		
		//span[normalize-space()='Add to cart']
		
		//addToCartBtn = 
		//addToCartBtn.isDisplayed();
		//addToCartBtn = driver.findElement(By.xpath("//span[text()='Add to Cart']"));
		//Action.scrollByVisibilityOfElement(driver, addToCartBtn);
		//addToCartBtn = Action.waitForElementToBeClickable(driver, addToCartBtn, 10);
		
		
		Action.click(getDriver(), addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		Action.fluentWait(addToCartMessage, 10);
		return Action.isDisplayed(getDriver(), addToCartMessage);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		
		proceedToCheckOutBtn = getDriver().findElement(By.xpath("//span[normalize-space()='Proceed to checkout']"));
		
		Action.fluentWait(proceedToCheckOutBtn, 10);
		Action.JSClick(getDriver(),proceedToCheckOutBtn);
		
		return new OrderPage();
	}
	
	
	
	
	

}
