package com.learning.base;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
public interface Element {
	
		
	void executeTheScript(String js, WebElement ele);
	
	/**
	 * This method will click the element and take snap
	 * @param ele   - The WebElement (button/link/element) to be clicked
	 * @throws StaleElementReferenceException (throws if element became stale and not available to interact)
	 */
	void click(WebElement ele);


	/**
	 * This method will enter the value in the given text field
	 * @param ele   - The WebElement (text field) in which the data to be entered
	 * @param data  - The data to be sent to The WebElement
	 * locateElement method in Browser Class
	 */
	 void append(WebElement ele, String data);

	/**
	 * This method will clear the value in the given text field
	 * @param ele   - The WebElement (text field) in which the data to be entered
	 * @throws InvalidElementStateException	(throws if not user-editable element)
	 */
	 void clear(WebElement ele);

	/**
	 * This method will clear and type the value in the given text field
	 * @param ele   - The WebElement (text field) in which the data to be entered
	 * @param data  - The data to be sent to The WebElement
	 */
	 void clearAndType(WebElement ele,String data);

	/**
	 * This method will get the visible text of the element
	 * @param ele   - The WebElement (button/link/element) in which text to be retrieved
	 */
	 String getElementText(WebElement ele);

	/**
	 * This method will get the Color values of the element
	 * @param ele   - The WebElement (button/link/element) in which text to be retrieved
	 * @return The visible text of this element.
	 */
	 String getBackgroundColor(WebElement ele);

	/**
	 * This method will get the text of the element text box
	 * @param ele   - The WebElement (button/link/element) in which text to be retrieved
	 * @return The attribute/property's current value (or) null if the value is not set.
	 */
	 String getTypedText(WebElement ele);


	/**
	 * This method will select the drop-down visible text
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param value The value to be selected (visible text) from the dropdown
	 */
	 void selectDropDownUsingText(WebElement ele, String value) ;

	/**
	 * This method will select the drop-down using index
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param index The index to be selected from the dropdown
	 */
	 void selectDropDownUsingIndex(WebElement ele, int index) ;

	/**
	 * This method will select the drop-down using index
	 * @param ele   - The WebElement (dropdown) to be selected
	 * @param value - The value to be selected (value) from the dropdown
	 */
	 void selectDropDownUsingValue(WebElement ele, String value) ;

	/**
	 * This method will verify exact given text with actual text on the given element
	 * @param ele   - The WebElement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @return true if the given object represents a String equivalent to this string, false otherwise
	 */
	 boolean verifyExactText(WebElement ele, String expectedText);

	/**
	 * This method will verify given text contains actual text on the given element
	 * @param ele   - The WebElement in which the text to be need to be verified
	 * @param expectedText  - The expected text to be verified
	 * @return true if this String represents the same sequence of characters as the specified string, false otherwise
	 */
	 boolean verifyPartialText(WebElement ele, String expectedText);

	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param ele   - The WebElement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @return true if this String represents the same sequence of characters as the specified value, false otherwise
	 */
	 boolean verifyExactAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify partial given attribute's value with actual value on the given element
	 * @param ele   - The WebElement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 */
	 void verifyPartialAttribute(WebElement ele, String attribute, String value);

	/**
	 * This method will verify if the element is visible in the DOM
	 * @param ele   - The WebElement to be checked
	 * @return true if the element is displayed or false otherwise
	 */
	 boolean verifyDisplayed(WebElement ele);

	/**
	 * This method will check the element to be invisible
	 * @param ele   - The WebElement to be checked
	 */
	 boolean verifyDisappeared(WebElement ele);

	/**
	 * This method will verify if the input element is Enabled
	 * @param ele   - The WebElement (Radio button, Checkbox) to be verified
	 * @return True if the element is enabled, false otherwise.
	 */
	 boolean verifyEnabled(WebElement ele);

	/**
	 * This method will verify if the element (Radio button, Checkbox) is selected
	 * @param ele   - The WebElement (Radio button, Checkbox) to be verified
	 * @return True if the element is currently selected or checked, false otherwise.
	 */
	 boolean verifySelected(WebElement ele);
	
}




