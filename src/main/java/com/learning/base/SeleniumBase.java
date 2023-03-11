package com.learning.base;

import com.learning.constants.Locators;
import com.learning.context.TestContextShared;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class SeleniumBase implements Browser, Element {
    protected Actions act;
    private final WebDriver driver;
    private final WebDriverWait shortWait;
    private final WebDriverWait longWait;

    public SeleniumBase(TestContextShared context) {
        this.driver = context.getDriver();
        this.shortWait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(15));
        this.longWait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(30));
        this.act = new Actions(context.getDriver());
    }

    protected String getAttribute(WebElement ele, String attributeValue) {
        String val = "";
        try {
            val = ele.getAttribute(attributeValue);
        } catch (WebDriverException e) {
            System.out.println("Attribue value not able to fetch :" + e.getMessage());
        }
        return val;
    }

    protected void moveToElement(WebElement ele) {
        act = new Actions(driver);
        act.moveToElement(ele).perform();
    }

    protected void dragAndDrop(WebElement eleSoutce, WebElement eleTarget) {
        act = new Actions(driver);
        act.dragAndDrop(eleSoutce, eleTarget).perform();
    }

    protected void contextClick(WebElement ele) {
        act = new Actions(driver);
        act.contextClick(shortWait.until(ExpectedConditions.elementToBeClickable(ele))).perform();
    }

    protected void hoverAndClick(WebElement ele) {
        act = new Actions(driver);
        act.moveToElement(shortWait.until(ExpectedConditions.elementToBeClickable(ele))).pause(5000).click().perform();
    }

    protected void doubleTap(WebElement ele) {
        act = new Actions(driver);
        act.click(shortWait.until(ExpectedConditions.elementToBeClickable(ele))).click().perform();
        System.out.println("Element moved");
    }

    protected void doubleClick(WebElement ele) {
        act = new Actions(driver);
        act.doubleClick(shortWait.until(ExpectedConditions.elementToBeClickable(ele))).perform();
        System.out.println("Element double clicked");
    }

    public void waitForAppearance(WebElement element) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            shortWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element did not appear after 20 seconds");

        }

    }


    @Override
    public void click(WebElement ele) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            ele.isDisplayed(); // @FindBy return the proxy even if it does not exist !!
        } catch (NoSuchElementException e) {
            System.out.println("The Element " + ele + " is not found");
        }

        String text = "";
        try {
            try {
                Thread.sleep(500);
                shortWait.until(ExpectedConditions.elementToBeClickable(ele));
                text = ele.getText();
                if (ele.isEnabled()) {
                    ele.click();
                } else {
                    jse.executeScript("arguments[0].click()", ele);
                }
            } catch (Exception e) {
                boolean bFound = false;
                int totalTime = 0;
                while (!bFound && totalTime < 10000) {
                    try {
                        Thread.sleep(500);
                        ele.click();
                        bFound = true;

                    } catch (Exception ex) {
                        bFound = false;
                    }
                    totalTime = totalTime + 500;
                }
                if (!bFound)
                    ele.click();
            }
        } catch (StaleElementReferenceException e) {
            System.err.println(e);
            System.out.println("The Element " + text + " could not be clicked due to:" + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println(e);
            System.out.println("The Element " + ele + " could not be clicked due to: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("The Element " + ele + " could not be clicked due to: " + e.getMessage());
        }
    }

    public void clickUsingJs(WebElement ele) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            ele.isDisplayed(); // @FindBy return the proxy even if it does not exist !!
        } catch (NoSuchElementException e) {
            System.out.println("The Element " + ele + " is not found");
        }

        String text = "";
        try {
            try {
                jse.executeScript("arguments[0].click()", ele);
            } catch (Exception e) {
                boolean bFound = false;
                int totalTime = 0;
                while (!bFound && totalTime < 10000) {
                    try {
                        Thread.sleep(500);
                        jse.executeScript("arguments[0].click()", ele);
                        bFound = true;

                    } catch (Exception ex) {
                        bFound = false;
                    }
                    totalTime = totalTime + 500;
                }
                if (!bFound)
                    jse.executeScript("arguments[0].click()", ele);
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("The Element " + text + " could not be clicked due to:" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " could not be clicked due to: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Element " + ele + " could not be clicked due to: " + e.getMessage());
        }
    }

    public void click(Locators locatorType, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String text = "";
        WebElement ele = locateElement(locatorType, value);
        try {
            try {
                Thread.sleep(500);
                shortWait.until(ExpectedConditions.elementToBeClickable(ele));
                text = ele.getText();
                if (ele.isEnabled()) {
                    ele.click();
                } else {
                    jse.executeScript("arguments[0].click()", ele);
                }
            } catch (Exception e) {
                boolean bFound = false;
                int totalTime = 0;
                while (!bFound && totalTime < 10000) {
                    try {
                        Thread.sleep(500);
                        ele = locateElement(locatorType, value);
                        ele.click();
                        bFound = true;

                    } catch (Exception ex) {
                        bFound = false;
                    }
                    totalTime = totalTime + 500;
                }
                if (!bFound)
                    ele.click();
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("The Element " + text + " could not be clicked " + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " could not be clicked \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Element " + ele + " could not be clicked \n" + e.getMessage());
        }
    }

    public void clickWithNoSnap(WebElement ele) {
        String text = ele.getText();
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("The Element " + ele + " could not be clicked \n" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " could not be clicked \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Element " + ele + " could not be clicked \n" + e.getMessage());
        }
    }

    @Override
    public void append(WebElement ele, String data) {
        try {
            String attribute = ele.getAttribute("value");
            if (attribute.length() > 1) {
                ele.sendKeys(data);
            } else {
                ele.sendKeys(data);
            }
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " could not be appended \n" + e.getMessage());
        }
    }

    @Override
    public void clear(WebElement ele) {
        try {
            ele.clear();
        } catch (ElementNotInteractableException e) {
            System.out.println("The field is not Interactable \n" + e.getMessage());
        }
    }

    /**
     * Overloaded method used to clear the existing value and type the data with
     * keys for tab or enter kind of
     *
     * @param ele  - WebElement from the DOM
     * @param data - Use to type and pass Keys as many needed
     */
    public void clearAndType(WebElement ele, CharSequence... data) {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys(data);
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) { // retry - 1
            pause(500);
            try {
                ele.sendKeys(data);
            } catch (Exception ex) {
                System.out.println("The Element " + ele + " did not allow to clear / type \n" + ex.getMessage());
            }
        }

    }

    @Override
    public void clearAndType(WebElement ele, String data) {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys("", "", data);
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) { // retry - 1
            pause(500);
            try {
                ele.sendKeys(data);
            } catch (Exception ex) {
                System.out.println("The Element " + ele + " did not allow to clear / type \n" + ex.getMessage());
            }
        }

    }

    public void clearAndType(Locators locatorType, String locatorValue, String data) {
        WebElement ele = locateElement(locatorType,locatorValue);
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys("", "", data);
        } catch (ElementNotInteractableException eleex) {
            System.out.println("The Element " + ele + " is not Interactable \n" + eleex.getMessage());
        } catch (WebDriverException wde) { // retry - 1
            pause(500);
            try {
                ele.sendKeys(data);
            } catch (Exception ex) {
                System.out.println("The Element " + ele + " did not allow to clear / type \n" + ex.getMessage());
            }
        }

    }

    public void typeAndTab(WebElement ele, String data) {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys("", "", data, Keys.TAB);
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        }

    }

    public void type(WebElement ele, String data) {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys("", "", data);
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        }

    }

    public void typeAndEnter(WebElement ele, String data) {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys("", "", data, Keys.ENTER);
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        }

    }

    @Override
    public String getElementText(WebElement ele) {
        try {
            String text = ele.getText();
            System.out.println("Text has been retrieved " + text);
            return text;
        } catch (WebDriverException e) {
            System.out.println("Sorry! text is not available \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Sorry! text is not available \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public String getBackgroundColor(WebElement ele) {
        String cssValue = null;
        try {
            cssValue = ele.getCssValue("color");
            System.out.println("The background color is " + cssValue);
        } catch (WebDriverException e) {
            System.out.println("Not able to get the background color \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Not able to get the background color \n" + e.getMessage());
        }
        return cssValue;
    }

    @Override
    public String getTypedText(WebElement ele) {
        String attributeValue = null;
        try {
            attributeValue = ele.getAttribute("value");
            System.out.println("The attribute value is " + attributeValue);
        } catch (WebDriverException e) {
            System.out.println("Not able to find attribute value \n" + e.getMessage());
        }
        return attributeValue;
    }

    @Override
    public void selectDropDownUsingText(WebElement ele, String value) {
        try {
            Select sel = new Select(ele);
            sel.selectByVisibleText(value);
        } catch (WebDriverException e) {
            System.out.println("Not able to select the drop down with text \n" + value);
        }
    }

    @Override
    public void selectDropDownUsingIndex(WebElement ele, int index) {
        try {
            Select sel = new Select(ele);
            sel.selectByIndex(index);
        } catch (WebDriverException e) {
            System.out.println("Not able to select the drop down with index " + index + " \n" + e.getMessage());
        }
    }

    @Override
    public void selectDropDownUsingValue(WebElement ele, String value) {
        try {
            Select sel = new Select(ele);
            sel.selectByValue(value);
        } catch (WebDriverException e) {
            System.out.println("Not able to select the drop down with value " + value + " \n" + e.getMessage());
        }
    }

    @Override
    public boolean verifyExactText(WebElement ele, String expectedText) {
        try {
            String text = ele.getText();
            if (text.contains(expectedText)) {
                return true;
            } else {
                System.out.println("The expected text " + text + "doesn't equals to the  " + expectedText);
            }
        } catch (WebDriverException e) {
            System.out.println("Unknown exception occured while verifying the Text \n" + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean verifyPartialText(WebElement ele, String expectedText) {
        try {
            if (ele.getText().contains(expectedText)) {
                return true;
            } else {
                System.out.println("The expected text doesn't contain the actual " + expectedText);
            }
        } catch (WebDriverException e) {
            System.out.println("Unknown exception occured while verifying the Text \n" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
        try {
            if (ele.getAttribute(attribute).equals(value)) {
                return true;
            } else {
                System.out.println("The expected attribute :" + attribute + " value does not contains the actual " + value);
            }
        } catch (WebDriverException e) {
            System.out.println("Unknown exception occured while verifying the Attribute Text \n" + e.getMessage());
        }
        return false;
    }

    @Override
    public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
        try {
            if (ele.getAttribute(attribute).contains(value)) {
                System.out.println("The expected attribute :" + attribute + " value contains the actual " + value);
            } else {
                System.out.println("The expected attribute :" + attribute + " value does not contains the actual " + value);
            }
        } catch (WebDriverException e) {
            System.out.println("Unknown exception occured while verifying the Attribute Text \n" + e.getMessage());
        }

    }

    @Override
    public boolean verifyDisplayed(WebElement ele) {
        try {
            if (ele.isDisplayed()) {
                return true;
            } else {
                System.out.println("The element " + ele + " is not visible");
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : \n" + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean verifyDisappeared(WebElement ele) {
        try {
            Boolean until = shortWait.until(ExpectedConditions.invisibilityOf(ele));
            System.out.println("Waited for an element to disappear");
            return until;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Element not disappeared \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Element not disappeared \n" + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean verifyEnabled(WebElement ele) {
        try {
            if (ele.isEnabled()) {
                return true;
            } else {
                System.out.println("The element " + ele + " is not Enabled");
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : \n" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean verifySelected(WebElement ele) {
        try {
            if (ele.isSelected()) {
                return true;
            } else {
                System.out.println("The element " + ele + " is not selected");
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : \n" + e.getMessage());
        }
        return false;

    }

    @Override
    public void startApp(String url, boolean headless) {
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.get(url);
            System.out.println("The Browser Launched in chrome browser with URL " + url);
        } catch (Exception e) {
            System.out.println("Something went wrong \n" + e.getMessage());
        }

    }


    @Override
    public WebElement locateElement(Locators locatorType, String value) {
        try {
            switch (locatorType) {
                case CLASS_NAME:
                    return driver.findElement(By.className(value));
                case CSS:
                    return driver.findElement(By.cssSelector(value));
                case ID:
                    return driver.findElement(By.id(value));
                case LINK_TEXT:
                    return driver.findElement(By.linkText(value));
                case NAME:
                    return driver.findElement(By.name(value));
                case PARTIAL_LINKTEXT:
                    return driver.findElement(By.partialLinkText(value));
                case TAGNAME:
                    return driver.findElement(By.tagName(value));
                case XPATH:
                    return driver.findElement(By.xpath(value));
                default:
                    System.err.println("Locator is not Valid");
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("The Element with locator:" + locatorType + " Not Found with value: " + value + "\n"
                    + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Element with locator:" + locatorType + " Not Found with value: " + value + "\n"
                    + e.getMessage());
        }
        return null;
    }

    @Override
    public WebElement locateElement(String value) {
        try {
            WebElement findElementById = driver.findElement(By.id(value));
            return findElementById;
        } catch (NoSuchElementException e) {
            System.out.println("The Element with locator id Not Found with value: " + value + "\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Element with locator id Not Found with value: " + value + "\n" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<WebElement> locateElements(Locators type, String value) {
        try {
            switch (type) {
                case CLASS_NAME:
                    return driver.findElements(By.className(value));
                case CSS:
                    return driver.findElements(By.cssSelector(value));
                case ID:
                    return driver.findElements(By.id(value));
                case LINK_TEXT:
                    return driver.findElements(By.linkText(value));
                case NAME:
                    return driver.findElements(By.name(value));
                case PARTIAL_LINKTEXT:
                    return driver.findElements(By.partialLinkText(value));
                case TAGNAME:
                    return driver.findElements(By.tagName(value));
                case XPATH:
                    return driver.findElements(By.xpath(value));
                default:
                    System.err.println("Locator is not Valid");
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("The Element with locator:" + type + " Not Found with value: " + value + "\n" + e.getMessage());
        }
        return null;
    }

    @Override
    public void switchToAlert() {
        try {
            driver.switchTo().alert();
            System.out.println("Focus has been switched to Alert");
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
    }

    @Override
    public void acceptAlert() {
        String text = "";
        try {
            shortWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            alert.accept();
            System.out.println("The alert " + text + " is accepted.");
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }

    }

    @Override
    public void dismissAlert() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            alert.dismiss();
            System.out.println("The alert " + text + " is accepted.");
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }

    }

    @Override
    public String getAlertText() {
        String text = "";
        try {
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            System.out.println("The alert text is " + text);
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : \n" + e.getMessage());
        }
        return text;
    }

    @Override
    public void typeAlert(String data) {
        try {
            driver.switchTo().alert().sendKeys(data);
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : \n" + e.getMessage());
        }
    }

    @Override
    public void switchToWindow(int index) {
        try {
            Set<String> allWindows = driver.getWindowHandles();
            List<String> allhandles = new ArrayList<String>(allWindows);
            driver.switchTo().window(allhandles.get(index));
            System.out.println("The Window With index: " + index + " switched successfully");
            System.out.println(driver.getTitle());
        } catch (NoSuchWindowException e) {
            System.out.println("The Window With index: " + index + " not found\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("The Window With index: " + index + " not found\n" + e.getMessage());
        }
    }

    @Override
    public boolean switchToWindow(String title) {
        try {
            Set<String> allWindows = driver.getWindowHandles();
            for (String eachWindow : allWindows) {
                driver.switchTo().window(eachWindow);
                if (driver.getTitle().equals(title)) {
                    break;
                }
            }
            System.out.println("The Window With Title: " + title + "is switched ");
            return true;
        } catch (NoSuchWindowException e) {
            System.out.println("The Window With Title: " + title + " not found");
        }
        return false;
    }

    @Override
    public void switchToFrame(int index) {
        try {
            Thread.sleep(100);
            driver.switchTo().frame(index);
        } catch (NoSuchFrameException e) {
            System.out.println("No such frame " + e.getMessage());
        } catch (Exception e) {
            System.out.println("No such frame " + e.getMessage());
        }

    }

    @Override
    public void switchToFrame(WebElement ele) {
        try {
            driver.switchTo().frame(ele);
        } catch (Exception e) {
            System.out.println("No such frame " + e.getMessage());
        }

    }

    public void switchToFrameUsingXPath(String xpath) {
        try {
            driver.switchTo().frame(locateElement(Locators.XPATH, xpath));
        } catch (Exception e) {
            System.out.println("No such frame " + e.getMessage());
        }

    }

    @Override
    public void switchToFrame(String idOrName) {
        try {
            driver.switchTo().frame(idOrName);
        } catch (NoSuchFrameException e) {
            System.out.println("No such frame " + e.getMessage());
        } catch (Exception e) {
            System.out.println("No such frame " + e.getMessage());
        }
    }

    @Override
    public void defaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("No such window " + e.getMessage());
        }
    }

    @Override
    public boolean verifyUrl(String url) {
        if (driver.getCurrentUrl().equals(url)) {
            System.out.println("The url: " + url + " matched successfully");
            return true;
        } else {
            System.out.println("The url: " + url + " not matched");
        }
        return false;
    }

    @Override
    public boolean verifyTitle(String title) {
        if (driver.getTitle().equals(title)) {
            System.out.println("Page title: " + title + " matched successfully");
            return true;
        } else {
            System.out.println("Page url: " + title + " not matched");

        }
        return false;
    }

    @Override
    public File takeSnapAsFile() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotImage = new File("");
        long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
        try {
            screenshotImage = screenshot.getScreenshotAs(OutputType.FILE);
        } catch (WebDriverException e) {
            System.out.println("The browser has been closed." + e.getMessage());
        } catch (Exception e) {
            System.out.println("The snapshot could not be taken " + e.getMessage());
        }
        return screenshotImage;
    }

    public byte[] takeSnapAsByte() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] screenshotByteImage = "".getBytes();
        try {
            screenshotByteImage = screenshot.getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            System.out.println("The browser has been closed." + e.getMessage());
        } catch (Exception e) {
            System.out.println("The snapshot could not be taken " + e.getMessage());
        }
        return screenshotByteImage;
    }

    @Override
    public synchronized void close() {
        try {
            pause(2000);
            if(Objects.nonNull(driver)) {
                driver.close();
            }
            System.out.println("Browser is closed");
        } catch (Exception e) {
            System.out.println("Browser cannot be closed " + e.getMessage());
        }
    }

    @Override
    public synchronized void quit() {
        try {
            pause(2000);
            if(Objects.nonNull(driver)) {
                driver.quit();
                System.out.println("All Browsers are closed");
            }
        } catch (Exception e) {
            System.out.println("All Browsers are closed" + e.getMessage());
        }
    }

    public void waitForDisappearance(WebElement element) {
        try {
            pause(5000);
            shortWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            System.out.println("Element did not appear after 10 seconds");

        }

    }

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chooseDate(WebElement ele, String data) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        try {
            jse.executeScript("arguments[0].setAttribute('value', '" + data + "')", ele);
            System.out.println("The Data :" + data + " entered Successfully");
        } catch (ElementNotInteractableException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        } catch (WebDriverException e) {
            System.out.println("The Element " + ele + " is not Interactable \n" + e.getMessage());
        }

    }

    public void fileUpload(WebElement ele, String data) {
        try {
            hoverAndClick(ele);
            pause(2000);

            // Store the copied text in the clipboard
            StringSelection stringSelection = new StringSelection(data);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            // Paste it using Robot class
            Robot robot = new Robot();

            // Enter to confirm it is uploaded
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("The file is selected Successfully");
        } catch (Exception e) {
            System.out.println("The file is not selected Successfully");
        }

    }

    public void fileUploadWithJs(WebElement ele, String data) {
        try {

            clickUsingJs(ele);
            ;
            pause(2000);

            // Store the copied text in the clipboard
            StringSelection stringSelection = new StringSelection(data);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            // Paste it using Robot class
            Robot robot = new Robot();

            // Enter to confirm it is uploaded
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("The file is selected Successfully");
        } catch (Exception e) {
            System.out.println("The file is not selected Successfully");
        }

    }

    @Override
    public void executeTheScript(String scriptToExecute, WebElement ele) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(scriptToExecute, ele);
    }


}
