package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import testDataAndUtils.Endpoints;
import testDataAndUtils.Payload;
import testDataAndUtils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition {
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;
    private Response response;

    private static String placeId;

    @Given("AddPlace payload with {string} {string} {string} is added")
    public void addPlace_payload_is_added(String placeName, String placeLanguage, String placeAddress) throws IOException {
        AddPlace bodyForAddPlaceEndpoint = Payload.getBodyForAddPlaceEndpoint(placeName, placeLanguage, placeAddress);
        requestSpecification = given().log().all().spec(Utils.getBaseRequestSpecification()).body(bodyForAddPlaceEndpoint);
    }

    @When("User sends {string} request to {string} endpoint")
    public void user_sends_request_to_endpoint(String httpMethod, String endPointName) {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        // Endpoints.valueOf() is calling the enum's constructor
        Endpoints endpoint = Endpoints.valueOf(endPointName);
        if (httpMethod.equalsIgnoreCase("Post"))
            response = requestSpecification.when().post(endpoint.getEndpoint())
                    .then().log().all().spec(responseSpecification)
                    .extract().response();
        else if (httpMethod.equalsIgnoreCase("Get"))
            response = requestSpecification.when().get(endpoint.getEndpoint())
                    .then().log().all().spec(responseSpecification)
                    .extract().response();
    }

    @Then("Response with status code {int}")
    public void response_with_status_code(int statusCodeValue) {
        assertEquals(statusCodeValue, response.getStatusCode());
    }

    @Then("{string} is {string} in response body")
    public void is_in_response_body(String key, String value) {
        assertEquals(value, Utils.getJsonPathFieldValue(response, key).toString());
    }

    @Then("Verify place_id maps to {string} using {string} endpoint")
    public void verify_place_id_maps_to_place_name(String expectedPlaceName, String endpointName) throws IOException {
        placeId = Utils.getJsonPathFieldValue(response, "place_id").toString();
        requestSpecification = given().log().all().spec(Utils.getBaseRequestSpecification()).queryParam("place_id", placeId);

        user_sends_request_to_endpoint("Get", endpointName);
        String actualPlaceName = Utils.getJsonPathFieldValue(response, "name").toString();
        assertEquals(expectedPlaceName, actualPlaceName);
    }

    // Second scenario, all non-static variables = null
    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
        requestSpecification = given().spec(Utils.getBaseRequestSpecification()).body(Payload.getBodyForDeletePlaceEndpoint(placeId));
    }

    public static String getPlaceId() {
        return placeId;
    }
}
