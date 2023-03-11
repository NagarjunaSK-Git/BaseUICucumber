package com.learning.pages;

import com.learning.constants.Locators;
import com.learning.base.SeleniumBase;
import com.learning.context.TestContextShared;
import org.openqa.selenium.WebDriver;

public class HomePage  {

    private final WebDriver driver;
    private final SeleniumBase seleniumBase;
    private final String dashboardTileLoc = ".oxd-topbar-header-breadcrumb";
    private final String viewMyInfoLoc = "a[href='/web/index.php/pim/viewMyDetails']";

    public HomePage(TestContextShared context) {
        this.seleniumBase = context.getSeleniumBase();
        this.driver = context.getDriver();
    }

    public Boolean dashboardVisible() {
        return seleniumBase.verifyDisplayed(seleniumBase.locateElement(Locators.CSS,dashboardTileLoc));
    }

    public void openMyInfo() {
        seleniumBase.click(Locators.CSS,viewMyInfoLoc);
        seleniumBase.pause(5000);
    }
}
