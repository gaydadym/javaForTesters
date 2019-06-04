package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ContactUpdatingTest extends TestBase {
    @Test
    public void testContactUpdating() throws Exception {
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().clickModifiy();
        app.getContactHelper().fillContactForm(new ContactData("Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"));
        app.getContactHelper().clickUpdate();

    }
}
