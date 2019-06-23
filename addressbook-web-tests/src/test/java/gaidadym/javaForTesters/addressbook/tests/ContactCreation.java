package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreation extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().clickAddContact();
        app.getContactHelper().fillContactForm(new ContactData("test4", "test", null, "test", "test_contact", "gaydadym+1@inbox.ru", "Москва, Новая Басманная, 4", "89999999999"),true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoMainPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before+1);
    }



}
