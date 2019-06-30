package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.appmanager.HelperBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactDelitionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("test4", "Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"));
            app.getNavigationHelper().gotoMainPage();
        }
    }

    @Test

    public void testContactDelition() throws Exception {

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        app.getContactHelper().deleteContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size()-1);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));

    }

}
