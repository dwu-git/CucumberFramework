package testDataAndUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    // static RequestSpecification to share the object between the tests
    private static RequestSpecification requestSpecification;
    public static RequestSpecification getBaseRequestSpecification() throws IOException {
        if (requestSpecification == null) {
            PrintStream logs = new PrintStream(new FileOutputStream("logging.txt"));
            return requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalPropertyBaseUrlValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logs))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                    .setContentType(ContentType.JSON).build();
        }
        return requestSpecification;
    }

    // global properties
    public static String getGlobalPropertyBaseUrlValue(String propertyName) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(propertyName);
    }

    public static Object getJsonPathFieldValue(Response response, String key) {
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        return js.get(key);
    }
}
