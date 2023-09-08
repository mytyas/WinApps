package puTTYPageObjectModel;

import baseUtilitiesPuTTY.Functions;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;

public class Configuration extends Functions {
    public Configuration(WindowsDriver driver){
        super(driver);
    }

    public Console setUpPutty(){
        driver.findElementByClassName("Edit").findElement(By.name("Host Name (or IP address)")).sendKeys("ipaddress");
        driver.findElementByName("Logging").click();
        driver.findElementByName("Printable output").click();
        driver.findElementByAccessibilityId("1050").clear();
        driver.findElementByAccessibilityId("1050").sendKeys("C:\\Users\\msencanski\\Documents\\putty.log");
        driver.findElementByAccessibilityId("1054").click();
        driver.findElementByName("Open").click();
        return new Console(driver);
    }

}
