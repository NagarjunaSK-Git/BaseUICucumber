package com.learning.stepdefinitions;

import com.learning.base.SeleniumBase;
import com.learning.context.TestContextShared;
import com.learning.factory.BrowserDriverFactory;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;
    private final TestContextShared context;

    public Hooks(TestContextShared context) {
        this.context = context;
    }

    @Before(order = 0)
    public void beforeHookDriverInitialization(Scenario scenario) {
        System.out.println("BEFORE FIRST: THREAD ID : " + Thread.currentThread().threadId() + "," +
                "SCENARIO NAME: " + scenario.getName());
        driver = new BrowserDriverFactory().initializeDriver();

    }

    @Before(order = 1)
    public void beforeHookSetDriver(Scenario scenario) {
        System.out.println("BEFORE FIRST: THREAD ID : " + Thread.currentThread().threadId() + "," +
                "SCENARIO NAME: " + scenario.getName());
        context.setDriver(driver);
        context.setSeleniumBase(new SeleniumBase(context));
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
       // Shall be Utilized for any before step hook action
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String fileName = scenario.getStatus() + "_" + scenario.getName();
        scenario.attach(context.getSeleniumBase().takeSnapAsByte(), "image/png", fileName);
    }

    @After(order = 1)
    public void afterHookScreenshot(Scenario scenario) {
        String fileName = scenario.getStatus() + "_" + scenario.getName();
        System.out.println("AFTER Taking Screenshot: THREAD ID : " + Thread.currentThread().threadId() + "," +
                "SCENARIO NAME: " + scenario.getName());
        if (scenario.isFailed()) {
            scenario.attach(context.getSeleniumBase().takeSnapAsByte(), "image/png", fileName);
        }
    }

    @After(order = 0)
    public void afterHookDriverDiscard(Scenario scenario) {
        context.getSeleniumBase().quit();
    }


}