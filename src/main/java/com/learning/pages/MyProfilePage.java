package com.learning.pages;

import com.learning.constants.Locators;
import com.learning.base.SeleniumBase;
import com.learning.context.TestContextShared;
import org.openqa.selenium.WebDriver;

public class MyProfilePage  {
    private final WebDriver driver;
    private final SeleniumBase seleniumBase;
    private final String bloodGroupDropdownLoc = "//label[contains(text(),'Blood Type')]/parent::div/following-sibling::div//div[@class='oxd-select-wrapper']";
    private final String bloodGroupTypeSaveButtonLoc = "//label[contains(text(),'Blood Type')]/ancestor::form//button";
    private String bloodGroupType = "//label[contains(text(),'Blood Type')]/parent::div/following-sibling::div//*[@role='option']/span[text()='PARAM_BG_TYPE']";

    public MyProfilePage(TestContextShared context) {
        this.seleniumBase = context.getSeleniumBase();
        this.driver = context.getDriver();
    }

    public MyProfilePage selectBloodGroup(String bloodGroup) {
        seleniumBase.click(Locators.XPATH,bloodGroupDropdownLoc);
        bloodGroupType = bloodGroupType.replace("PARAM_BG_TYPE", bloodGroup);
        System.out.println("Blood Group xPath :"+bloodGroupType);
        seleniumBase.click(Locators.XPATH,bloodGroupType);
        return this;
    }

    public MyProfilePage saveBloodGroup() {
        seleniumBase.click(Locators.XPATH,bloodGroupTypeSaveButtonLoc);
        seleniumBase.pause(5000);
        return this;
    }
}
