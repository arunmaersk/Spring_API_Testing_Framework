package com.maersk.ohm.sit.stepdefinition;


import com.maersk.ohm.sit.config.ConfigReader;
import com.maersk.ohm.sit.springapplication.CucumberSpringApplication;
import helper.CustomSoftAssertions;
import helper.EndPoints;
import helper.RestAssuredHelper;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.request.PetOrderReq;
import net.serenitybdd.rest.SerenityRest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;

@SpringBootTest(classes = CucumberSpringApplication.class)
public class Feature1StepDefinition {
    Scenario scenario;

    FilterableRequestSpecification requestSpecification = (FilterableRequestSpecification) SerenityRest.given();
    Response response;
    @Autowired
    ConfigReader configReader;

    @Autowired
    RestAssuredHelper restAssuredHelper;

    @Autowired
    CustomSoftAssertions customSoftAssertions;

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        this.scenario = scenario;
        requestSpecification.baseUri(configReader.getBaseURI()).relaxedHTTPSValidation();
    }


    @Given("User provides the resource endPoint")
    public void user_provides_the_resource_end_point() {
        System.out.println("End Point: " + configReader.getBaseURI());
        System.out.println("Thread Name : " + Thread.currentThread().getName());
        System.out.println("Thread ID : " + Thread.currentThread().getId());

    }

    @Given("User provides the petId {string}")
    public void user_provides_the_pet_id(String id) {
        System.out.println("ID: " + id);

    }

    @Given("User provides the order quantity {string}")
    public void user_provides_the_order_quantity(String qty) {
        System.out.println("Qty: " + qty);
    }

    @Given("User provides the order status {string}")
    public void user_provides_the_order_status(String string) {
        System.out.println("Order status: Placed");
    }

    @When("User hits a POST request")
    public void user_hits_a_post_request() {
        PetOrderReq petOrderReq = new PetOrderReq();
        String path = configReader.getApiVersion() + EndPoints.TEMPLATE_PET_STORE_ORDER.getValue();
        requestSpecification
                .headers("Content-Type", "application/json")
                .basePath(path)
                .body(petOrderReq);
        response = restAssuredHelper.postRequest(requestSpecification);

    }

    @Given("Validate response status code is {int}")
    public void validate_response_status_code_is(int statusCode) {
       // Assert.assertEquals(statusCode, response.getStatusCode());
        customSoftAssertions.softAssertEquals("status",String.valueOf(200),String.valueOf(300));
    }

    @Then("Validate schema of the api response")
    public void validate_schema_of_the_api_response() {
        File schema = new File(System.getProperty("user.dir") + "/src/test/resources/schema/PetOrderSchema.json");
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Then("Validate response body is as expected")
    public void validate_response_body_is_as_expected() {
        PetOrderReq petOrderReq = response.as(PetOrderReq.class);
        Assert.assertEquals("placed", petOrderReq.getStatus());
        Assert.assertEquals(Integer.valueOf(1), petOrderReq.getQuantity());

    }

    @Then("Validate softAssertions response body is as expected")
    public void validate_softAssertions_response_body_is_as_expected() throws Exception {
        PetOrderReq petOrderReq = response.as(PetOrderReq.class);
        customSoftAssertions.softAssertEquals("Quantity", Integer.valueOf(5), petOrderReq.getQuantity());
        customSoftAssertions.softAssertEquals("Status", "replaced", petOrderReq.getStatus());

    }

    @When("User hits a Sample POST request")
    public void user_hits_a_Sample_post_request() {
        PetOrderReq petOrderReq = new PetOrderReq();
        petOrderReq.setId("4");
        petOrderReq.setStatus("replaced");

        String path = configReader.getApiVersion() + EndPoints.TEMPLATE_PET_STORE_ORDER.getValue();
        requestSpecification
                .headers("Content-Type", "application/json")
                .basePath(path)
                .body(petOrderReq);
        response = restAssuredHelper.postRequest(requestSpecification);

    }

    @And("Validate response body with the POJO is as expected")
    public void validateResponseBodyWithThePOJOIsAsExpected() throws Exception {
        PetOrderReq petOrderReq = new PetOrderReq();
        PetOrderReq petResponse = restAssuredHelper.mapToPojo(response, PetOrderReq.class);
        Assert.assertEquals(petOrderReq, petResponse);

    }

    @Then("Validate hardAssertions response body is as expected")
    public void validate_hardAssertions_response_body_is_as_expected() throws Exception {
        PetOrderReq petOrderReq = response.as(PetOrderReq.class);
        Assert.assertEquals("replacement", petOrderReq.getStatus());
        Assert.assertEquals(Integer.valueOf(2), petOrderReq.getQuantity());
        Assert.assertFalse(petOrderReq.getComplete());

    }

     @After
    public void afterAll() {
        if(customSoftAssertions.getSoftAssert()!=null) {
            customSoftAssertions.assertAll();
        }
    }
}


