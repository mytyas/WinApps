package outlook;

import baseUtilities.Functions;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Mail extends Functions {
public Mail(WindowsDriver driver){
    super(driver);
}




    public OutlookBase send() throws MalformedURLException, InterruptedException {
        Thread.sleep(5000);
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
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), winCapabilities);

        String to = "Name Surname";
        String cc = "Name Surname";
        String subject = "Title";
        String body = "Body";

        WebElement emailText = driver.findElement(By.xpath("//Window/Pane/Pane/Pane/Pane/Document/Edit"));
        WebElement fieldTo = driver.findElement(By.xpath("//Window/Pane/Pane/Edit[1]"));
        WebElement fieldCC = driver.findElement(By.xpath("//Window/Pane/Pane/Edit[2]"));
        WebElement fieldSubject = driver.findElement(By.xpath("//Window/Pane/Pane/Edit[3]"));
        WebElement attachment = driver.findElement(By.name("Attach File..."));
        WebElement btnSend = driver.findElement(By.name("Send"));

        Actions performAct = new Actions(driver);
        Thread.sleep(5000);
        fieldTo.click();
        performAct.sendKeys(to).build().perform();
        performAct.sendKeys((Keys.ENTER));

        fieldCC.click();
        performAct.sendKeys(cc).build().perform();
        performAct.sendKeys((Keys.ENTER));


        fieldSubject.click();
        performAct.sendKeys(subject).build().perform();
        performAct.sendKeys((Keys.ENTER));

        emailText.click();
        performAct.moveToElement(emailText).build().perform();
        performAct.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME)).build().perform();
        performAct.sendKeys(body).build().perform();

        attachment.click();
        performAct.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).build().perform();

        performAct.click(btnSend);
        performAct.sendKeys(Keys.RIGHT, Keys.ENTER).build().perform();
        return null;
    }
}
