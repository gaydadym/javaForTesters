package gaidadym.javaForTesters.addressbook.tests;


import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src\\test\\resources\\screen.jpg");
        list.add(new Object[]{new ContactData().withNickname("Test 1").withFirstname("Header 1").withLastname("Footer 1")
                .withGroup("test2").withAddress("Test4").withEmail("test@test.com").withEmail2("test2@test.com")
                .withEmail3("test3@test.com").withHomePhone("1111111").withMobilePhone("222222").withWorkPhone("33333")
                .withMiddlename("Testovich").withPhoto(photo)});
        list.add(new Object[]{new ContactData().withNickname("Test 2").withFirstname("Header 2").withLastname("Footer 2")
                .withGroup("test2").withAddress("Test2").withEmail("test@test2.com").withEmail2("test2@test2.com")
                .withEmail3("test3@test2.com").withHomePhone("21111111").withMobilePhone("3222222").withWorkPhone("433333")
                .withMiddlename("Testovich2").withPhoto(photo)});

        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {

        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }

}
