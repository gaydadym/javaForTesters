package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactDelitionTest extends TestBase {
    @Test
    public void testContactDelition() throws Exception {
        app.getContactHelper().viewContactDetails();
        app.getContactHelper().clickModifiy();
        app.getContactHelper().clickDelete();

    }
}
