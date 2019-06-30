package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactUpdatingTest extends TestBase {
    @BeforeMethod
    private void ensurePrecondition() {
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact((new ContactData("test4", "new_user","new_user","new_user","new_user","new_user@gmail.com","new_user","99999999999")));
            app.getNavigationHelper().gotoMainPage();
        }
    }

    @Test
    public void testContactUpdating() throws Exception {
        ensurePrecondition();
        ContactData contact = new ContactData(null, "Updated1","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999");
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        app.getContactHelper().updateContact(contact, index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertNotEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }




}
