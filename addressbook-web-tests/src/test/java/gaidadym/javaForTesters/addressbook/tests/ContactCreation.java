package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class ContactCreation extends TestBase {
    @Test
    public void testContactCreation() throws Exception {

        ContactData contact = new ContactData();
        contact.withNickname("test4");
        contact.withFirstname("test");
        contact.withLastname("test");
        contact.withGroup("test2");
        contact.withAddress("Test4");
        contact.withEmail("test@test.com");
        contact.withPhone("48421654");
        contact.withMiddlename("Testovich");
        Set<ContactData> before = app.contact().all();
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(after,before);
    }



}
