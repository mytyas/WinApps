package stepImplementations;


import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import testing.Putty;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PuttyTest {

    Putty putty = new Putty();
    String name = "eperitest01";
    String password = "eperitest01";
    String command = "ls -a";
    String command2 = "ls -al /etc";


    public PuttyTest() throws AWTException {
    }

    @Given("^user starts PuTTY$")
    public void user_starts_PuTTY() throws AWTException {
        putty.setUp();
    }
    @When("^user sets up PuTTY$")
    public void user_sets_up_PuTTY() throws AWTException, IOException {
        putty.driver.findElementByClassName("Edit").findElement(By.name("Host Name (or IP address)")).sendKeys("10.42.2.51");
        putty.driver.findElementByName("Logging").click();
        putty.driver.findElementByName("Printable output").click();
        putty.driver.findElementByAccessibilityId("1050").clear();
        putty.driver.findElementByAccessibilityId("1050").sendKeys("C:\\Users\\msencanski\\Documents\\putty.log");
        putty.driver.findElementByAccessibilityId("1054").click();
    }
    @And("^user opens PuTTY")
    public void user_opens_PuTTY(){
        putty.driver.findElementByName("Open").click();
    }
    @And("^user logs In")
            public void user_logs_In() throws MalformedURLException {
/*        putty.type(name);
        putty.type(password);*/
        DesiredCapabilities desktopCapabilities = new DesiredCapabilities();
        desktopCapabilities.setCapability("app", "Root");
        WindowsDriver DesktopSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), desktopCapabilities);
        WebElement winWebElement = DesktopSession.findElement(By.xpath("//Window"));
        String winHandleStr = winWebElement.getAttribute("NativeWindowHandle");
        System.out.println(winHandleStr);
        int winHandleInt = Integer.parseInt(winHandleStr);
        String winHandleHex = Integer.toHexString(winHandleInt);
        DesiredCapabilities winCapabilities = new DesiredCapabilities();
        winCapabilities.setCapability("appTopLevelWindow", winHandleHex);
        putty.driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), winCapabilities);

        WebElement console= putty.driver.findElementByXPath("//Window");


        console.click();
        Actions performAct = new Actions(putty.driver);
        performAct.moveToElement(console).build().perform();
        performAct.sendKeys(name).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
        performAct.sendKeys(password).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();


    }
    @And("^user passes the commands")
    public void user_passes_the_commands(){
        Actions performAct = new Actions(putty.driver);
        performAct.sendKeys(command).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
        performAct.sendKeys(command2).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
  /*      putty.type(command);
        putty.type(command2);*/


    }
    @Then("^user verifies the output")
    public void user_verifies_the_output() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\msencanski\\Documents\\putty.log"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        System.out.println(content);

        Assert.assertTrue(content.contains(".profile"));
    }

}
