package cucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/resources/features/Outlook.feature", glue = "stepImplementationsMail",publish = true)
public class OutlookRunner extends AbstractTestNGCucumberTests{
    public void sendEmail() {
    }
}
