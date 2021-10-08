package steps.web;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import utils.TestWebDriver;

public class BrowserTabTitleSteps {

    @Given("User navigate to {string} in web browser")
    public void userNavigateTo(String url){
        TestWebDriver.get().get(url);
    }

    @Then("Correct title {string} is displayed in web browser tab")
    public void correctTitleIsDisplayedInWebBrowserTab(String title) {
        Assert.assertEquals(TestWebDriver.get().getTitle(), title);
    }
}
