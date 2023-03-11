package com.learning.stepdefinitions;

import com.learning.context.TestContextShared;
import com.learning.factory.ApplicationPageFactory;
import com.learning.pages.HomePage;
import com.learning.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginStepDefinitions {

    private final TestContextShared context;
    private final LoginPage loginpage;
    private final HomePage homepage;

    public LoginStepDefinitions(TestContextShared context) {
        this.context = context;
        loginpage = ApplicationPageFactory.getLoginPage(context);
        homepage = ApplicationPageFactory.getHomePage(context);
    }

    @Given("I login with existing {string} and {string}")
    public void i_login_with_existing_username_and_password(String username, String password) throws InterruptedException {
        loginpage.load();
        loginpage.enterUserName(username)
                .enterPassword(password)
                .submit();

    }

    @Then("I should see {string} message")
    public void i_should_see_invalid_credentials_message(String invalidCredentialsErrTextExpected) {
        String errorMessageReceived = loginpage.getInvalidCredentialsErrorText();
        Assert.assertEquals(errorMessageReceived, invalidCredentialsErrTextExpected);
    }
}
