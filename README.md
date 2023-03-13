# BaseUICucumber
An End-to-End UI Test Automation Framework based upon Selenium WebDriver (4.8+), Cucumber(7.11+), TestNG(7.11+), Maven.

[![UI Automaiton Tests](https://github.com/NagarjunaSK-Git/BaseUICucumber/actions/workflows/cucumbertests.yml/badge.svg)](https://github.com/NagarjunaSK-Git/BaseUICucumber/actions/workflows/cucumbertests.yml)

## Quick Start
1) Install [Java JDK19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)
2) Download [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## Tech stack
<a href="https://www.selenium.dev" target="_blank" rel="noreferrer"> 
<img src="https://avatars.githubusercontent.com/u/983927?s=200&v=4" alt="selenium" width="40" height="40"/> </a>
<a href="https://cucumber.io/" target="_blank" rel="noreferrer">
<img src="https://avatars.githubusercontent.com/u/320565?s=200&v=4" alt="Cucumber" width="40" height="40"/> </a>
<a href="https://testng.org/" target="_blank" rel="noreferrer"> 
<img src="https://avatars.githubusercontent.com/u/12528662?s=200&v=4" alt="testng" width="40" height="40"/> </a>
<a href="https://www.java.com" target="_blank" rel="noreferrer">
<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a>
<a href="https://www.jenkins.io" target="_blank" rel="noreferrer"> 
<img src="https://www.vectorlogo.zone/logos/jenkins/jenkins-icon.svg" alt="jenkins" width="40" height="40"/> </a> 

## Running tests through testng xml
Create or Select the required cucumber_testng xml -> Right click and select Run

## Running tests through Maven
Run test using command `mvn clean test`

# Other Maven Command Samples
* `mvn clean test "-Dcucumber.filter.tags=@smoke" "-Dparallelbrowsercount=1" "-Dbrowser=EDGE" "-Denv=UAT"`
* `mvn clean test "-Dcucumber.filter.tags=@regression" "-Dparallelbrowsercount=2" "-Dbrowser=Chrome" "-Denv=Qa"`
* `mvn clean test "-Dparallelbrowsercount=1"`
* `mvn clean test "-Dcucumber.filter.tags=@regression" "-Dparallelbrowsercount=1"`

##  Key Features
* Supports cross browser testing in local.

* Page object model design.

* Supports Cucumber BDD

* Supports filtering of test scenarios with cucumber tags during maven runtime execution

* Supports parallel execution of scenarios which is configurable during maven runtime execution

* Supports environment,browser selection during maven runtime execution

* Supports capturing screenshots Extent Report Adapter plugin (Step Level) and Cucumber JVM Reports

* Ability to configure and schedule jenkins job to build triggers automatically
