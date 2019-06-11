package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.appmanager.HelperBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDelitionTest extends TestBase {
    @Test
    public void testContactDelition() throws Exception {
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("test4", "Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"));
            app.getNavigationHelper().gotoMainPage();
        }
        Thread.sleep(3000);
        app.getContactHelper().selectContact();
        app.getContactHelper().clickDelete();
        app.getNavigationHelper().closeAlertWindow();

    }
}
