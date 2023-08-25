package puTTYPageObjectModel;


import baseUtilitiesPuTTY.Functions;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Console extends Functions {
    public Console(WindowsDriver driver) {
        super(driver);
    }
    String name = "eperitest01";
    String password = "eperitest01";
    String command = "ls -a";
    String command2 = "ls -al /etc";

    public Console enterConsole() throws IOException {
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
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), winCapabilities);

        WebElement console = driver.findElementByXPath("//Window");

        Actions performAct = new Actions(driver);
        console.click();
        performAct.moveToElement(console).build().perform();
        performAct.sendKeys(name).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
        performAct.sendKeys(password).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();

        performAct.sendKeys(command).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
        performAct.sendKeys(command2).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();


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
        return null;
    }
}