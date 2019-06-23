package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.appmanager.HelperBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDelitionTest extends TestBase {
    @Test
    public void testContactDelition() throws Exception {
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("test4", "Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"));
            app.getNavigationHelper().gotoMainPage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().clickDelete();
        app.getNavigationHelper().closeAlertWindow();
        app.getNavigationHelper().gotoMainPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before-1);

    }
}
