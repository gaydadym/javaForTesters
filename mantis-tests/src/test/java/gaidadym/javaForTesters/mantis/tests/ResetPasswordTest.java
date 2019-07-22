package gaidadym.javaForTesters.mantis.tests;

import gaidadym.javaForTesters.mantis.model.MailMessage;
import gaidadym.javaForTesters.mantis.model.MantisUsers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        startMailServer();
        app.session().login("administrator","admin");

    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        List<MantisUsers> users = app.db().users();
        MantisUsers user = users.iterator().next();
        String password = "changedPassword";
        app.goTo().userManagePage();
        app.goTo().selectUserById(user.getId());
        app.goTo().clickResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finishResetingPass(confirmationLink,user.getUsername(), password);
        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    public void startMailServer(){
        app.mail().start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}

