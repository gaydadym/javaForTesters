package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactUpdatingTest extends TestBase {
    @BeforeMethod
    private void ensurePrecondition() {
        if (app.contact().all().size()==0){
            app.contact().create((new ContactData().withFirstname("test1").withLastname("Testovich").withMiddlename("Testoviy").withGroup("test2").withAddress("Воронеж, возле котенка на Лизюкова")));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testContactUpdating() throws Exception {
        ensurePrecondition();
        Contacts before = app.contact().all();
        ContactData updatedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(updatedContact.getId()).withFirstname("Updated").withLastname("Updated")
                .withAddress("Updated").withEmail("Updated").withEmail2("test@test.com").withEmail3("test3@test3.com");
        app.contact().update(contact);
        assertThat(app.group().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(updatedContact).withAdded(contact)));
    }




}
