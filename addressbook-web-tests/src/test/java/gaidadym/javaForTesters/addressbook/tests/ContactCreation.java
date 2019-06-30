package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withNickname("test4").withFirstname("test").withLastname("test").withGroup("test4");
        app.contact().create(contact);
        app.goTo().mainPage();
        List<ContactData> after = app.contact().list();
        before.add(after.get(after.size()-1));
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }



}
