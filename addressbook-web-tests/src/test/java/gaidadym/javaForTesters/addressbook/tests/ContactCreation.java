package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().fillContactForm(new ContactData("test", "test", "test", "test_contact", "gaydadym+1@inbox.ru", "Москва, Новая Басманная, 4", "89999999999"));
        app.getContactHelper().submitContactCreation();
    }



}
