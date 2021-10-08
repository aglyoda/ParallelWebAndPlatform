package steps.service;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.TestProperties;

public class BasicServiceTestSteps {

    private static Response response;

    @Given("^API client is initialised$")
    public void apiClientIsInitialised() {
        RestAssured.baseURI = TestProperties.SERVICE_HOST_URL;
    }

    @When("^I execute GET (.*) request$")
    public void iExecuteGETUsersRequest(String endpoint) {
        response = RestAssured.get(endpoint);
    }


    @Then("I receive {int} response code")
    public void iReceiveResponseCode(int code) {
        Assert.assertEquals(response.getStatusCode(), code);
    }
}
