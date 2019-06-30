package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickAddContact();
        app.getContactHelper().fillContactForm(new ContactData("test4", "test", null, "test", "test_contact", "gaydadym+1@inbox.ru", "Москва, Новая Басманная, 4", "89999999999"),true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.add(after.get(after.size()-1));
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }



}
