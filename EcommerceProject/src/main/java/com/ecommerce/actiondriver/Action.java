package com.ecommerce.actiondriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.base.BaseClass;

public class Action extends BaseClass{
	
	public static void implicitWait(WebDriver ldriver,int time) {
		// Set implicit wait for 10 seconds
	    ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	
	
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return destination;
		
		/*
		
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
		
		*/
	}
	
	
	
	
	public static void pageLoadTimeOut(WebDriver ldriver,int time) {
		// Set implicit wait for 10 seconds
		ldriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
	}
	
	 

	public static void scrollByVisibilityOfElement (WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
		//js.executeScript("window.scrollBy(0, -100);"); 	
	
	}
	
	/*
	public static void scrollScreenByCoordinate (WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
		js.executeScript("window.scrollBy(0, 200);"); 	
	
	}
	*/
	
	public static void scrollScreenByCoordinate(WebDriver driver, int x, int y) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);  
	}

	
	
	
	public static void click(WebDriver ldriver, WebElement locatorName) {
		Actions act = new Actions(getDriver());
		act.moveToElement(locatorName).click().build().perform();
	}
	
	public static boolean findElement(WebDriver ldriver,WebElement element) {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
		}catch(Exception e) {
			flag = false;
			
		} finally {
			if(flag) {
				System.out.println("Successfully found Element at");
			}else {
				System.out.println("Unable to Locate Element at");
			}
		}
		
	
	return flag;
}


public static boolean isDisplayed(WebDriver ldriver,WebElement element ) {
	boolean flag = false;
	flag = findElement(ldriver, element);
	if(flag) {
		flag = element.isDisplayed();
		if(flag) {
			System.out.println("Element is displayed");
		}else {
			System.out.println("Element is not displayed");
		}}
	else {
			System.out.println("Not Displayed");
		}
	
	return flag;
	}


public static boolean isSelected (WebDriver ldriver, WebElement element) {
boolean flag = false;
flag = findElement(ldriver, element);

if(flag) {
	flag = element.isSelected();
	
	if(flag) {
		System.out.println("Element is selected");
	}else {
		System.out.println("Element is not selected");
	}
}
else {
	System.out.println("Not selected");
}

return flag;
}


public static boolean isEnabled (WebDriver ldriver, WebElement element) {
boolean flag = false;
flag = findElement(ldriver, element);

if(flag) {
	flag = element.isEnabled();
	
	if(flag) {
		System.out.println("Element is Enabled");
	}else {
		System.out.println("Element is not Enabled");
	}
}
else {
	System.out.println("Not Enabled");
}

return flag;
}



public static boolean type (WebElement element, String text) {
boolean flag = false;


try {
	flag = element.isDisplayed();
	element.clear();
	element.sendKeys(text);
	flag = true;
} catch(Exception e) {
	System.out.println("Location Not Found");
	flag = false;
} finally {
	if(flag) {
		System.out.println("Successfuly Entered Value");
	} else {
		System.out.println("Unable to enter Value");
	}
}


return flag;
}


public static boolean selectBySendKeys(WebElement element, String value){
	boolean flag = false;
	try {
		element.sendKeys(value);
		flag = true;
		return true;
	}catch(Exception e) {
		return false;
	}finally {
		if (flag) {
			System.out.println("Select Value from Dropdown");
		}else {
			System.out.println("Not Selected value from the Dropdown");
		}
	}
}

public static boolean selectByIndex(WebElement element, int index) {
	boolean flag = false;
	
	try {
		Select s = new Select(element);
		s.selectByIndex(index);
		flag=true;
		return true;
		
	}catch(Exception e) {
		return false;
	}finally {
		if(flag) {
			System.out.println("Option selected by Index");
		}else {
			System.out.println("Option not selected By Index");
		}
	}
}

public static boolean selectByValue(WebElement element, String value) {
	boolean flag = false;
	
	try {
		Select s = new Select(element);
		s.selectByValue(value);
		flag=true;
		return true;
		
	}catch(Exception e) {
		return false;
	}finally {
		if(flag) {
			System.out.println("Option selected by Value");
		}else {
			System.out.println("Option not selected By Value");
		}
	}
}

public static boolean selectByVisibleText(WebElement element, String visibleText) {
	boolean flag = false;
	
	try {
		Select s = new Select(element);
		s.selectByVisibleText(visibleText);
		flag=true;
		return true;
		
	}catch(Exception e) {
		return false;
	}finally {
		if(flag) {
			System.out.println("Option selected by visibleText");
		}else {
			System.out.println("Option not selected By visibleText");
		}
	}
}


public static boolean mouseHoverByJavaScript(WebElement locator) {
	boolean flag = false;
	try {
		WebElement mo = locator;
		String javaScript = "var evObj = document.createEvent('MouseEvent');"
                + "evObj.initMouseEvent('mouseover', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "arguments[0].dispatchEvent(evObj);";

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript,mo);
		flag = true;
		return true;
	}
	
	catch(Exception e) {
		return false;
	} finally {
		if(flag) {
			System.out.println("MouseOver Action is performed");
		}else {
			System.out.println("MouseOver Action is not performed");
		}
	}
}

public static boolean JSClick(WebDriver driver, WebElement element) {
    boolean flag = false;
    try {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        flag = true;
    } catch (Exception e) {
        System.out.println("Error occurred while performing JavaScript click: " + e.getMessage());
        throw e;
    } finally {
        if (flag) {
            System.out.println("Click Action is performed");
        } else {
            System.out.println("Click Action is not performed");
        }
    }
    return flag;
}

// Method to switch to a frame by its index
public static boolean switchToFrameByIndex(WebDriver driver, int index) {
    boolean flag = false;
    try {
        new WebDriverWait(driver, Duration.ofSeconds(14))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        flag = true;
    } catch (Exception e) {
        System.out.println("Error while switching to frame by index: " + e.getMessage());
    } finally {
        if (flag) {
            System.out.println("Frame with index \"" + index + "\" is selected");
        } else {
            System.out.println("Frame with index \"" + index + "\" could not be selected");
        }
    }
    return flag;
}

// Method to switch to a frame by its ID or name
public static boolean switchToFrameById(WebDriver driver, String idValue) {
    boolean flag = false;
    try {
        driver.switchTo().frame(idValue);
        flag = true;
    } catch (Exception e) {
        System.out.println("Error while switching to frame by ID: " + e.getMessage());
    } finally {
        if (flag) {
            System.out.println("Frame with ID \"" + idValue + "\" is selected");
        } else {
            System.out.println("Frame with ID \"" + idValue + "\" could not be selected");
        }
    }
    return flag;
}

// Method to switch to a frame by its name
public static boolean switchToFrameByName(WebDriver driver, String name) {
    boolean flag = false;
    try {
        driver.switchTo().frame(name);
        flag = true;
    } catch (Exception e) {
        System.out.println("Error while switching to frame by name: " + e.getMessage());
    } finally {
        if (flag) {
            System.out.println("Frame with name \"" + name + "\" is selected");
        } else {
            System.out.println("Frame with name \"" + name + "\" could not be selected");
        }
    }
    return flag;
}



	public static void fluentWait(WebElement element, int timeout) {
	    Wait<WebDriver> wait = new FluentWait<>(getDriver())
	        .withTimeout(Duration.ofSeconds(timeout))
	        .pollingEvery(Duration.ofSeconds(1))
	        .ignoring(NoSuchElementException.class);

	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	

}




