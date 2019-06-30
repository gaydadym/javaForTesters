package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactUpdatingTest extends TestBase {
    @BeforeMethod
    private void ensurePrecondition() {
        if (app.contact().list().size()==0){
            app.contact().create((new ContactData().withFirstname("test1").withLastname("Testovich").withMiddlename("Testoviy")));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testContactUpdating() throws Exception {
        ensurePrecondition();
        ContactData contact = new ContactData().withFirstname("Updated").withLastname("Updated")
                .withAddress("Updated").withEmail("Updated");
        Set<ContactData> before = app.contact().all();
        int index = before.size()-1;
        app.contact().update(contact, index);
        Set<ContactData> after = app.contact().all();
        Assert.assertNotEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }




}
