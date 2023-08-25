package cucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/resources/features", glue = "stepImplementations",publish = true)

public class PuttyRunner extends AbstractTestNGCucumberTests {

    public void setUpScenario() {
    }
}
