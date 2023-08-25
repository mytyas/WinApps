package baseUtilities;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Functions extends Base{

    public Functions(WindowsDriver driver) {
        this.driver=driver;
    }


    public Functions() {

    }
}
