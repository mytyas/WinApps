package outlook;
import baseUtilities.Functions;
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

public class OutlookBase extends Functions {


    public OutlookBase(WindowsDriver driver) {
        super(driver);
    }


    public Mail openOutlook() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement newEmail= driver.findElement(By.name("New Email"));
        newEmail.click();
        return new Mail(driver);
    }

}

