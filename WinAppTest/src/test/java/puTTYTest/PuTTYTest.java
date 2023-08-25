package puTTYTest;

import baseUtilitiesPuTTY.Base;
import org.testng.annotations.Test;
import puTTYPageObjectModel.Configuration;
import puTTYPageObjectModel.Console;

import java.io.IOException;


public class PuTTYTest extends Base {
@Test
    public void logInServer() throws IOException {
        Configuration configuration = new Configuration(driver);
        Console console=configuration.setUpPutty();
        console.enterConsole();
    }
}