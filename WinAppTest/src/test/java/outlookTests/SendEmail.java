package outlookTests;

import baseUtilities.Base;
import org.testng.annotations.Test;
import outlook.Mail;
import outlook.OutlookBase;

public class SendEmail extends Base {

    @Test
    public void sendEmailTest() throws Exception {
    OutlookBase outlookBase=new OutlookBase(driver);
    Mail mail=outlookBase.openOutlook();
    mail.send();
    }
}
