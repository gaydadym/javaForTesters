package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactUpdatingTest extends TestBase {
    @Test
    public void testContactUpdating() throws Exception {
        if (! app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact((new ContactData("test4", "new_user","new_user","new_user","new_user","new_user@gmail.com","new_user","99999999999")));
            app.getNavigationHelper().gotoMainPage();
        }
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().clickModifiy();
        app.getContactHelper().fillContactForm(new ContactData(null, "Updated","Updated","Updated","Updated","Updated@gmail.com","Updated","99999999999"),false);
        app.getContactHelper().clickUpdate();

    }
}
