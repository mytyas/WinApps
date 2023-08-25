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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class OutlookTests {
    public WindowsDriver driver = null;
    public Robot robot;

    public OutlookTests() throws AWTException {
        robot = new Robot();
    }
    int ctrl=KeyEvent.VK_CONTROL;
    int v=KeyEvent.VK_V;
    int f=KeyEvent.VK_F;
    int tab=KeyEvent.VK_TAB;
    int alt=KeyEvent.VK_ALT;
    int s=KeyEvent.VK_S;
    int enter = KeyEvent.VK_ENTER;
    int n = KeyEvent.VK_N;
    int a = KeyEvent.VK_A;

    @BeforeMethod
    public void setUp(){
        DesiredCapabilities cap = new DesiredCapabilities();
//windows application id needs to be given to open the app. Run Get-StartApps in powershell to get all windows app ids.
// Or, write the path to the executable file
        cap.setCapability("app","C:\\Program Files\\Microsoft Office\\root\\Office16\\OUTLOOK.EXE\"");
//add platformname and device name
        cap.setCapability("platformName", "Windows");
        cap.setCapability("deviceName", "WindowsPC");

        try {
//create webdriver instance
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), cap);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
//provide implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void teardown(){
        System.out.println("test run successful");
    }

    @Test
    public void sendEmailOutlook() throws Exception {
        String to = "milansencanski@gmail.com";
        String cc = "milansencanski@gmail.com";
        String subject = "Mail";
        String body = "Prvi email sa attachmentom iz nase automatizacije";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement newEmail= driver.findElement(By.name("New Email"));
        newEmail.click();

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

        WebElement emailText= driver.findElement(By.xpath("//Window/Pane/Pane/Pane/Pane/Document/Edit"));
        WebElement fieldTo= driver.findElement(By.xpath("//Window/Pane/Pane/Edit[1]"));
        WebElement fieldCC= driver.findElement(By.xpath("//Window/Pane/Pane/Edit[2]"));
        WebElement fieldSubject= driver.findElement(By.xpath("//Window/Pane/Pane/Edit[3]"));
        WebElement attachment= driver.findElement(By.name("Attach File..."));
        WebElement btnSend= driver.findElement(By.name("Send"));

        Actions performAct = new Actions(driver);

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
        performAct.sendKeys(Keys.ARROW_DOWN,Keys.ENTER).build().perform();
        btnSend.click();
        performAct.sendKeys(Keys.RIGHT,Keys.ENTER).build().perform();


        }


    @Test
    public void filterUnreadEmailOutlook(){
        driver.findElementByName("Filter Email").click();
        driver.findElementByName("Unread").click();
    }
    @Test
    public void searchEmailOutlook(){
        String search ="Ivana Bunijevac";
        WebElement fieldSearch= driver.findElementByName("Search");

        Actions performAct = new Actions(driver);

        fieldSearch.click();
        performAct.sendKeys(search).build().perform();
        performAct.sendKeys((Keys.ENTER));


    }

}
