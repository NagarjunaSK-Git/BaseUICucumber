package com.learning.pages;

import com.learning.constants.Locators;
import com.learning.base.SeleniumBase;
import com.learning.context.TestContextShared;
import com.learning.utils.EnvConfigLoader;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final SeleniumBase seleniumBase;
    private final String usernameLoc = "username";
    private final String passwordLoc = "password";
    private final String loginButtonLoc = "button[type='submit']";
    private final String invalidCredentialsErrLoc = "//p[normalize-space()='Invalid credentials']";

    private final String dashboardTileLoc = ".oxd-topbar-header-breadcrumb";

    public LoginPage(TestContextShared context) {
        this.seleniumBase = context.getSeleniumBase();
        this.driver = context.getDriver();
    }

    public LoginPage load() {
        driver.get(EnvConfigLoader.getInstance().getApplicationUrl());
        return this;
    }

    public LoginPage enterCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        submit();
        return this;
    }

    public LoginPage enterUserName(String usernameData) {
        seleniumBase.clearAndType(Locators.NAME, usernameLoc, usernameData);
        return this;
    }

    public LoginPage enterPassword(String passwordData) {
        seleniumBase.clearAndType(Locators.NAME, passwordLoc, passwordData);
        return this;
    }

    public void submit() {
        seleniumBase.click(Locators.CSS, loginButtonLoc);
        seleniumBase.waitForAppearance(seleniumBase.locateElement(Locators.CSS,dashboardTileLoc));
    }

    public String getInvalidCredentialsErrorText() {
        return seleniumBase.getElementText(seleniumBase.locateElement(Locators.XPATH, invalidCredentialsErrLoc));
    }

}
