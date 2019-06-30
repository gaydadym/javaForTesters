package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.appmanager.HelperBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactDelitionTest extends TestBase {
    @Test
    public void testContactDelition() throws Exception {
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("test4", "Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"));
            app.getNavigationHelper().gotoMainPage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().clickDelete();
        app.getNavigationHelper().closeAlertWindow();
        app.getNavigationHelper().gotoMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size()-1);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));

    }
}
