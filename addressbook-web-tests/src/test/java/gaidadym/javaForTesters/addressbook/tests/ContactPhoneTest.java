package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts(false).size()==0){
            app.contact().create((new ContactData().withGroup("test2").withFirstname("test1")
                    .withLastname("Testovich").withMiddlename("Testoviy").withHomePhone("545164").withMobilePhone("564453354").withWorkPhone("444")));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s)-> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)-> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return  phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
