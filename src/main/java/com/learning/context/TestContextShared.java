package com.learning.context;

import com.learning.base.SeleniumBase;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
/**
 * This Class consists of Common elements used across the framework and it requires thread specific isolated objects
 * Will be used and injected via constructor
 * constructor Dependency Injection will be supplied as thread specific manner by Pico Container dependency
 * **/
@Getter
@Setter
public class TestContextShared {

    private WebDriver driver;

    private SeleniumBase seleniumBase;


}
