package testDataAndUtils;

import pojo.AddPlace;
import pojo.Location;

import java.util.List;

public class Payload {
    public static AddPlace getBodyForAddPlaceEndpoint(String placeName, String placeLanguage, String placeAddress) {
        AddPlace bodyForAddPlaceApi = new AddPlace();
        bodyForAddPlaceApi.setLocation(new Location(-38.383494, 33.427362));
        bodyForAddPlaceApi.setAccuracy(50);
        bodyForAddPlaceApi.setName(placeName);
        bodyForAddPlaceApi.setPhoneNumber("(+91) 983 893 3937");
        bodyForAddPlaceApi.setAddress(placeAddress);
        bodyForAddPlaceApi.setTypes(List.of("shoe park", "shop"));
        bodyForAddPlaceApi.setWebsite("https://rahulshettyacademy.com");
        bodyForAddPlaceApi.setLanguage(placeLanguage);
        return bodyForAddPlaceApi;
    }

    public static String getBodyForDeletePlaceEndpoint(String placeId) {
        return "{\r\n\"place_id\":\"" + placeId + "\"\r\n}";
    }
}
