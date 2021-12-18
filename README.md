# ParallelWebAndPlatform
Gradle based Selenium and RestAssured framework for testing Web UI and API applications

## Tech Stack

- Gradle (Build management)/ Java
- Selenium Webdriver (Web UI Testing)
- Rest Assured (API Testing)
- Cucumber (BDD - Behaviour Driven Development)
- TestNG (TDD -Test Driven Development)
- Extent Report (For test reporting) using Cucumber Event Listeners (not using any adapters)

## Salient Features

- Create Web Test Scenarios in Gherkin language
- Create API Test Scenarios in Gherkin language
- Run tests in Parallel (Both Web and API)
- Run UI tests on various resolutions
- Run UI tests on various devices (phone, tablet etc...)
- Two types of reports: Extent report with screenshots for failed scenarios, Basic HTML reports for screenshots on each test scenario

## Why to use

- If you need a single framework for both Web UI and Platform API tests
- Writing test scenarios in Gherkin (BDD)
- Running tests in Parallel to reduce run time
- If there are budget constraints for buying Saucelabs/Perfecto licences, scenarios can be run of different browsers (Chrome, Firefox, Edge ...) and also across emulated cross devices (phones, tablets, iOS, Android ...)

## How to use

###### Drivers
Add web drivers for your respective web browsers here. 

###### Devices
Add devices which you want to emulate here. You will need Device width in pixels, device height in pixel and pixel ratio. For example: for iPhoneX width is 375 pixel, height is 812 pixel and pixel ratio is 2.0

###### Environments
Add properties file for the environments you want to run your test scenarios in. Generally the enviornments are DEV, QA and UAT.

<img width="674" alt="Screenshot 2021-12-18 at 1 31 16 PM" src="https://user-images.githubusercontent.com/36156465/146634158-a4a6ce03-add0-403c-8db3-61f26a50b12c.png">

###### Run - run.properties
- Here you need to specify in how many threads you need to run the tests
- If you need to run the tests in emulated devices, if yes; which device
- also the resolution of the web browser e.g. 800X600
- The Title and name of the test report
- If basic HTML report needs to be generated having screens for each and every test scenarios run

You can either run the tests using TestNG or by creating Gradle test task using useTestNG()

## Note
- This is a continuousaly evolving framework and you can easily extend it by adding additionaly functionalities as per your requirements
- There might be some glitches here and there, please do share if you encounter any.
