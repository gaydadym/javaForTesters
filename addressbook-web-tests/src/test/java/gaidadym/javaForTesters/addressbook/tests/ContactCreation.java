package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }



}
