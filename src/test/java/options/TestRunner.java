package options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"stepDefinitions"}, plugin = {
        "pretty",
        "json:target/cucumber-report/cucumber.json",
        "html:target/cucumber-report/cucumber.html"}, monochrome = true)
public class TestRunner {

}
