package com.learning.stepdefinitions;

import com.learning.context.TestContextShared;
import com.learning.factory.ApplicationPageFactory;
import com.learning.pages.HomePage;
import com.learning.pages.MyProfilePage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HomeStepDefinitions {

    private final TestContextShared context;
    private final HomePage homepage;
    private final MyProfilePage myProfilePage;

    public HomeStepDefinitions(TestContextShared context) {
        this.context = context;
        homepage = ApplicationPageFactory.getHomePage(context);
        myProfilePage = ApplicationPageFactory.getMyProfilePage(context);
    }


    @Then("I should see dashboard")
    public void i_should_see_dashboard() {
        boolean isHomeLandingPageVisible = homepage.dashboardVisible();
        Assert.assertTrue(isHomeLandingPageVisible,"Home Page Tile Visible");
    }

    @Then("I update the blood group {string}")
    public void i_update_blood_group_details(String bloodGrp) throws InterruptedException {
        homepage.openMyInfo();
        myProfilePage.selectBloodGroup(bloodGrp.strip())
                     .saveBloodGroup();
    }
}
