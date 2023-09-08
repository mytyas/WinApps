package testing;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class WinSCP2 {
    String name = "username";
    String password = "password";
    public WindowsDriver driver = null;
    @BeforeMethod
    public void setUp() {
        DesiredCapabilities cap = new DesiredCapabilities();
//windows application id needs to be given to open the app. Run Get-StartApps in powershell to get all windows app ids.
// Or, write the path to the executable file
        cap.setCapability("app", "C:\\Program Files (x86)\\WinSCP\\WinSCP.exe\"");
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



    @Test
    public void logIn() throws InterruptedException, MalformedURLException {
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

        WebElement logger= driver.findElementByXPath("//Window/Window");

        Actions performAct = new Actions(driver);
        logger.click();
        performAct.moveToElement(logger).build().perform();

        //Host name
        performAct.click(driver.findElement(By.xpath("//Window/Window/Pane/Pane/Pane/Edit[@ClassName='TEdit'][2]")));
        performAct.sendKeys("ipaddress").build().perform();

        //User name
        performAct.click(driver.findElement(By.xpath("//Window/Window/Pane/Pane/Pane/Edit[@ClassName='TEdit'][1]")));
        performAct.sendKeys("eperitest01").build().perform();

/*        //port number
        performAct.click(driver.findElement(By.xpath("//Window/Window/Pane/Pane/Pane/Edit")));
        performAct.sendKeys(Keys.chord(Keys.DELETE));
        performAct.sendKeys(Keys.chord(Keys.DELETE));
        performAct.sendKeys("eperitest01").build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();*/

        //password
        performAct.click(driver.findElement(By.xpath("//Window/Window/Pane/Pane/Pane/Edit[@ClassName='TPasswordEdit']")));
        performAct.sendKeys("eperitest01").build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();


        WebElement baseWindow= driver.findElementByXPath("//Window");

        baseWindow.click();
        performAct.moveToElement(baseWindow).build().perform();
        //select file
        performAct.click(driver.findElement(By.xpath("//Window/Pane/Datau0020fGrid/Item/Edit[@Name='adobe_install_log.txt']"))).build().perform();
        performAct.sendKeys(Keys.F5).build().perform();
        performAct.sendKeys(Keys.chord(Keys.ENTER)).build().perform();



    }
}
