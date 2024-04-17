package cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// features = "src/main/java/features/PlaceApiValidation.feature" when there are several feature files in the package, and one needs to run only PlaceApiValidation.feature
// plugin = "json:target/jsonReports/cucumber-report.json" for reporting
@CucumberOptions(features = "src/main/java/features", plugin = "json:target/jsonReports/cucumber-report.json", glue = { "stepDefinitions" }, tags = "@DeletePlace")
public class TestRunner {
}
