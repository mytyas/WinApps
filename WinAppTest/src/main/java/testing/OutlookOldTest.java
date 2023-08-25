package testing;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OutlookOldTest {
    public WindowsDriver driver = null;
    public Robot robot;
    public OutlookOldTest() throws AWTException {
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

    public void pressTwoKeyShortcut(int key1, int key2){
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.keyRelease(key2);
        robot.keyRelease(key1);
    }

    public void pressOneKeyShortcut(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    public void pressThreeKeyShortcut(int key1, int key2, int key3){
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.keyPress(key3);
        robot.keyRelease(key3);
        robot.keyRelease(key2);
        robot.keyRelease(key1);
    }
    public void pressFourKeyShortcut(int key1, int key2, int key3, int key4){
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.keyPress(key3);
        robot.keyPress(key4);
        robot.keyRelease(key4);
        robot.keyRelease(key3);
        robot.keyRelease(key2);
        robot.keyRelease(key1);
    }
    public void addAttachment(){
        pressFourKeyShortcut(alt,n,a,f);
        pressOneKeyShortcut(enter);
    }

    public void type(String string){

        try {
            Robot robot = new Robot();

            robot.delay(1000);
            for (char c : string.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {

                }
                else {
                    try {
                        robot.keyPress(keyCode);
                        robot.delay(10);
                        robot.keyRelease(keyCode);
                        robot.delay(10);
                    }
                    catch (Exception e) {
                        if (c=='_') {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_MINUS);
                            robot.keyRelease(KeyEvent.VK_MINUS);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                        if (c==':') {
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_SEMICOLON);
                            robot.keyRelease(KeyEvent.VK_SEMICOLON);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                        }
                        if (c=='/'){
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
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test
    public void sendEmailOutlook() throws Exception {
        String to = "Milan Sencanski";
        String cc = "Milan Sencanski";
        String subject = "Email";
        String body = "Email with an attachment";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        pressTwoKeyShortcut(ctrl,n);
        type(to);
        pressOneKeyShortcut(tab);
        type(cc);
        pressOneKeyShortcut(tab);
        type(subject);
        pressOneKeyShortcut(tab);
        type(body);
        addAttachment();
        pressTwoKeyShortcut(alt,s);
    }

    @Test
    public void filterUnreadEmailOutlook(){
        driver.findElementByName("Filter Email").click();
        driver.findElementByName("Unread").click();
    }
    @Test
    public void searchEmailOutlook(){
        int enter = KeyEvent.VK_ENTER;
        driver.findElementByName("Search").sendKeys("Ivana Bunijevac");
        pressOneKeyShortcut(enter);
    }
}
