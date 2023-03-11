package com.learning.factory;

import com.learning.context.TestContextShared;
import com.learning.pages.HomePage;
import com.learning.pages.LoginPage;
import com.learning.pages.MyProfilePage;
import org.openqa.selenium.WebDriver;

public class ApplicationPageFactory {

    private static LoginPage loginPage;
    private static HomePage homePage;
    private static MyProfilePage myProfilePage;
    public static LoginPage getLoginPage(TestContextShared context) {
        return loginPage == null ? new LoginPage(context) : loginPage;
    }

    public static HomePage getHomePage(TestContextShared context) {
        return homePage == null ? new HomePage(context) : homePage;
    }

    public static MyProfilePage getMyProfilePage(TestContextShared context) {
        return myProfilePage == null ? new MyProfilePage(context) : myProfilePage;
    }


}