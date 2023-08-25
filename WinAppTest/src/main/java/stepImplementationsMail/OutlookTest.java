package stepImplementationsMail;
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
import testing.OutlookTests;


import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OutlookTest {
    OutlookTests outlookTests = new OutlookTests();
    String to = "Ivana Bunijevac";
    String cc = "Ivana Bunijevac";
    String subject = "Mail";
    String body = "Prvi email sa attachmentom iz nase automatizacije";


    public OutlookTest() throws AWTException {
    }

    @Given("^user starts Outlook$")
    public void user_starts_Outlook(){
        outlookTests.setUp();
    }
    @When("^opens a new email$")
    public void opens_a_new_email(){
        outlookTests.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement newEmail= outlookTests.driver.findElement(By.name("New Email"));
        newEmail.click();
    }

    @And("^adds To$")
    public void adds_To() throws MalformedURLException {
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
        outlookTests.driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), winCapabilities);
        Actions performAct = new Actions(outlookTests.driver);

        WebElement fieldTo= outlookTests.driver.findElement(By.xpath("//Window/Pane/Pane/Edit[1]"));






        fieldTo.click();
        performAct.sendKeys(to).build().perform();
        performAct.sendKeys((Keys.ENTER));
    }

    @And("^adds Cc$")
    public void adds_Cc(){
        Actions performAct = new Actions(outlookTests.driver);
        WebElement fieldCC= outlookTests.driver.findElement(By.xpath("//Window/Pane/Pane/Edit[2]"));
        fieldCC.click();
        performAct.sendKeys(cc).build().perform();
        performAct.sendKeys((Keys.ENTER));
    }
    @And("^adds Subject$")
    public void adds_Subject(){
        Actions performAct = new Actions(outlookTests.driver);
        WebElement fieldSubject= outlookTests.driver.findElement(By.xpath("//Window/Pane/Pane/Edit[3]"));
        fieldSubject.click();
        performAct.sendKeys(subject).build().perform();
        performAct.sendKeys((Keys.ENTER));
    }
    @And("^adds Email text$")
    public void adds_Email_text() {
        Actions performAct = new Actions(outlookTests.driver);
        WebElement emailText= outlookTests.driver.findElement(By.xpath("//Window/Pane/Pane/Pane/Pane/Document/Edit"));
        emailText.click();
        performAct.moveToElement(emailText).build().perform();
        performAct.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME)).build().perform();
        performAct.sendKeys(body).build().perform();
    }

    @And("^adds Attachment$")
    public void adds_Attachment() {
        Actions performAct = new Actions(outlookTests.driver);
        WebElement attachment= outlookTests.driver.findElement(By.name("Attach File..."));
        attachment.click();
        performAct.sendKeys(Keys.ARROW_DOWN,Keys.ENTER).build().perform();
    }
    @Then("^sends an email$")
        public void sends_an_email(){
            Actions performAct = new Actions(outlookTests.driver);
            WebElement btnSend= outlookTests.driver.findElement(By.name("Send"));
            btnSend.click();
            performAct.sendKeys(Keys.RIGHT,Keys.ENTER).build().perform();
        }
    }

