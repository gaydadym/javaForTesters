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
            app.contact().create((new ContactData().withFirstname("test1")
                    .withLastname("Testovich").withMiddlename("Testoviy").withHomePhone("545164").withMobilePhone("564453354").withWorkPhone("444")));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(app.contact().mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(),equalTo(app.contact().mergeEmails(contactInfoFromEditForm)));

    }

    public static String cleaned(String phone){
        return  phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
