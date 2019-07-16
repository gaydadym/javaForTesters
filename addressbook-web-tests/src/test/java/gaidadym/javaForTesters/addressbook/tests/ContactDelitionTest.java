package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelitionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        Groups groups = app.db().groups(false);
        if (app.db().contacts(false).size()==0){
            app.contact().create((new ContactData().withFirstname("test1")
                    .withLastname("Testovich").withMiddlename("Testoviy").withPhoto(new File("src/test/resources/screen.jpg")).withGroupName(groups.iterator().next().getName())));
            app.goTo().mainPage();
        }
    }

    @Test

    public void testContactDelition() throws Exception {

        Set<ContactData> before = app.db().contacts(false);
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Set<ContactData> after = app.db().contacts(false);
        assertThat(after, equalTo(((Contacts) before).without(deletedContact)));
        verifyContactListInUI();

    }

}
