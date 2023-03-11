package com.learning.base;

import com.learning.constants.Locators;
import org.openqa.selenium.*;

import java.io.File;
import java.util.List;

public interface Browser {
	
	/**
	 * This method will launch the Chrome browser and 
	 * maximise the browser and set the wait for 30 seconds 
	 * and load the url
	 * @param url - This will load the specified url
	 */	
	  void startApp(String url, boolean headless);

	/**
	 * This method will locate the element using any given locator
	 * @param locatorType  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 * @return The first matching element on the current context.
	 */
	 WebElement locateElement(Locators locatorType, String locValue);
	
	/**
	 * This method will locate the element using id
	 * @param locValue - The locator value by which the element to be found
	 * @return The first matching element on the current context.
	 */
	 WebElement locateElement(String locValue);
	
	/**
	 * This method will locate all matching element using any given locator
	 * @param locatorType  - The locator by which the element to be found
	 * @param locValue - The locator value by which the element to be found
	 * @return A list of all WebElements, or an empty list if nothing matches.
	 */
	 List<WebElement> locateElements(Locators locatorType, String locValue);
	
	/**
	 * This method will switch to the Alert
	 */
	  void switchToAlert();
	/**
	 * This method will accept the alert opened
	 */
	 void acceptAlert();
	
	/**
	 * This method will dismiss the alert opened
	 */
	 void dismissAlert();
	
	/**
	 * This method will return the text of the alert
	 */
	 String getAlertText();

	/**
	 * This method will enter the value in the alert
	 * @param data- the data to be entered in alert
	 */
	 void typeAlert(String data);
	
	/**
	 * This method will switch to the Window of interest
	 * @param index The window index to be switched to. 0 -> first window
	 */
	 void switchToWindow(int index);
	
	/**
	 * This method will switch to the Window of interest using its title
	 * @param title The window title to be switched to first window
	 */
	 boolean switchToWindow(String title);
	
	/**
	 * This method will switch to the specific frame using index
	 * @param index   - The int (frame) to be switched
	 */
	 void switchToFrame(int index);
	
	/**
	 * This method will switch to the specific frame
	 * @param ele   - The WebElement (frame) to be switched
	 * @throws NoSuchFrameException, StaleElementReferenceException 
	 */
	 void switchToFrame(WebElement ele);

	/**
	 * This method will switch to the specific frame using id (or) Name
	 * @param idOrName   - The String (frame) to be switched
	 */
	 void switchToFrame(String idOrName);
	
	/**
	 * This method will switch to the first frame on the page
	 */
	 void defaultContent();
	
	/**
	 * This method will verify browser actual url with expected
	 * @param url   - The url to be checked
	 * @return true if the given object represents a String equivalent to this url, false otherwise
	 */
	 boolean verifyUrl(String url);
	
	/**
	 * This method will verify browser actual title with expected
	 * @param title - The expected title of the browser
	 * @return true if the given object represents a String equivalent to this title, false otherwise
	 */
	 boolean verifyTitle(String title);
	
	/**
	 * This method will take snapshot of the browser
	 * @return Object in which is stored information about the screenshot.
	 */
	 File takeSnapAsFile();

	/**
	 * This method will close the active browser
	 */
	 void close();
	
	/**
	 * This method will close all the browsers
	 */
	 void quit();

	
	
	
	

	
}
