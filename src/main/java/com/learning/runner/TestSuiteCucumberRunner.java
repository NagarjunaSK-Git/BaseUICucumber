package com.learning.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"html:target/cucumber/index.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.learning.stepdefinitions"},
        monochrome = true,
        tags = "@smoke",
        features = "src/main/resources/features/"
)
public class TestSuiteCucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}