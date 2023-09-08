package testing;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Putty {
    public WindowsDriver driver = null;
    public Robot robot;

    public Putty() throws AWTException {
        robot = new Robot();
    }

    @BeforeMethod
    public void setUp() {
        DesiredCapabilities cap = new DesiredCapabilities();
//windows application id needs to be given to open the app. Run Get-StartApps in powershell to get all windows app ids.
// Or, write the path to the executable file
        cap.setCapability("app", "C:\\Program Files\\PuTTY\\putty.exe\"");
//add platformname and device name
        cap.setCapability("platformName", "Windows");
        cap.setCapability("deviceName", "WindowsPC");

        try {
//create webdriver instance
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//provide implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        System.out.println("test run successful");
    }

/*    public void pressTwoKeyShortcut(int key1, int key2) {
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.keyRelease(key2);
        robot.keyRelease(key1);
    }

    public void pressOneKeyShortcut(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
    }*/

 /*   public void type(String string) {

        try {
            Robot robot = new Robot();

            robot.delay(1000);
            for (char c : string.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {

                } else {
                    try {
                        robot.keyPress(keyCode);
                        robot.delay(10);
                        robot.keyRelease(keyCode);
                        robot.delay(10);
                    } catch (Exception e) {
                        if (c == '_') {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_MINUS);
                            robot.keyRelease(KeyEvent.VK_MINUS);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                        if (c == ':') {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_SEMICOLON);
                            robot.keyRelease(KeyEvent.VK_SEMICOLON);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                        if (c == '/') {
                            robot.keyPress(KeyEvent.VK_ALT);
                            robot.keyPress(KeyEvent.VK_NUMPAD0);
                            robot.keyPress(KeyEvent.VK_NUMPAD4);
                            robot.keyPress(KeyEvent.VK_NUMPAD7);
                            robot.keyRelease(KeyEvent.VK_NUMPAD7);
                            robot.keyRelease(KeyEvent.VK_NUMPAD4);
                            robot.keyRelease(KeyEvent.VK_NUMPAD0);
                            robot.keyRelease(KeyEvent.VK_ALT);
                        }
                    }
                }
            }
            robot.keyPress(KeyEvent.VK_ENTER);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }*/


    @Test
    public void puttyControl() throws AWTException, IOException, InterruptedException {

        String name = "username;
        String password = "password";
        String command = "ls -a";
        String command2 = "ls -al /etc";

        driver.findElementByClassName("Edit").findElement(By.name("Host Name (or IP address)")).sendKeys("10.42.2.51");
        driver.findElementByName("Logging").click();
        driver.findElementByName("Printable output").click();
        driver.findElementByAccessibilityId("1050").clear();
        driver.findElementByAccessibilityId("1050").sendKeys("C:\\Users\\msencanski\\Documents\\putty.log");
        driver.findElementByAccessibilityId("1054").click();

        driver.findElementByName("Open").click();
//        type(name);
//        type(password);
//        type(command);
//        type(command2);
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

        WebElement console= driver.findElementByXPath("//Window");

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
    }
}

