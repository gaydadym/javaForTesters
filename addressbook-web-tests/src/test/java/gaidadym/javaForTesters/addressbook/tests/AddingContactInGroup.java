package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class AddingContactInGroup extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        Set<ContactData> before = app.db().contacts(false);
        ContactData addedContact = before.iterator().next();

        if (app.db().groups(false).size()==0 || app.contact().freeGroupsForContact(addedContact).size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("misha_test").withHeader("Header").withFooter("Footer"));
            app.goTo().mainPage();
        }
        if (app.db().contacts(false).size()==0){
            app.contact().create((new ContactData().withFirstname("test1")
                    .withLastname("Testovich").withMiddlename("Testoviy").withPhoto(new File("src/test/resources/screen.jpg"))));
            app.goTo().mainPage();
        }
    }

    @Test
    public void testAddingContactInGroup() throws Exception {


        Set<ContactData> before = app.db().contacts(false);
        ContactData addedContact = before.iterator().next();
        GroupData freegroupForContact = app.contact().freeGroupsForContact(addedContact).iterator().next();
        Groups beforeGroups = addedContact.getGroups();
        app.contact().addContactInGroup(addedContact,freegroupForContact);
        Groups  afterGroups = app.db().refreshContact(addedContact.getId()).getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.withAdded(freegroupForContact)));



    }
}
