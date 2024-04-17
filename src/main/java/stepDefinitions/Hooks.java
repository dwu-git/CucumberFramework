package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    // Works when there is (tags = "@DeletePlace") in TestRunner
    @Before("@DeletePlace")
    public void beforeDeletePlaceScenario() throws IOException {
        StepDefinition stepDefinition = new StepDefinition();
        if (StepDefinition.getPlaceId() == null) {
            //Create place_id to delete the place then
            stepDefinition.addPlace_payload_is_added("Place Name Here", "French", "France");
            stepDefinition.user_sends_request_to_endpoint("Post", "AddPlace");
            stepDefinition.verify_place_id_maps_to_place_name("Place Name Here", "GetPlace");
        }
    }
}
