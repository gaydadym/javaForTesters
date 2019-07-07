package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelitionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (app.contact().list().size()==0){
            app.contact().create((new ContactData().withFirstname("test1")
                    .withLastname("Testovich").withMiddlename("Testoviy")));
            app.goTo().mainPage();
        }
    }

    @Test

    public void testContactDelition() throws Exception {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        assertThat(after, equalTo(((Contacts) before).without(deletedContact)));

    }

}
