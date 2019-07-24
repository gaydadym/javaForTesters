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

public class DelitionContactFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        GroupData group = new GroupData().withName("Test").withFooter("Footer").withHeader("Header");
        if (app.db().groups(false).size()==0){
            app.goTo().groupPage();
            app.group().create(group);
        }
        if (app.db().groupsForAllContacts().size()==0||app.db().contacts(false).size()==0){
            app.contact().create(new ContactData().withLastname("Ivanov").withFirstname("Ivan").withGroupName(group.getName()).withPhoto(new File("src/test/resources/screen.jpg")));
            app.goTo().mainPage();
        }
        Contacts contacts = app.db().contacts(false);
        ContactData deletedContact = null;
        for(ContactData contact: contacts){
            if(contact.getGroups().size()>0){
                deletedContact = contact;
            }
        }
        if(deletedContact == null){
            deletedContact = contacts.iterator().next();
            app.contact().addContactInGroup(deletedContact,app.db().groups(false).iterator().next());
        }
    }

    @Test
    public void testDelitingContactFromGroup() throws Exception {
        Contacts contacts = app.db().contacts(false);
        ContactData deletedContact = null;
        for(ContactData contact: contacts){
            if(contact.getGroups().size()>0){
                deletedContact = contact;
            }
        }
        GroupData deletedGroup = deletedContact.getGroups().iterator().next();
        Groups beforeGroups = deletedContact.getGroups();
        app.contact().deleteContactFromGroup(deletedContact,deletedGroup);
        Groups afterGroups = app.db().refreshContact(deletedContact.getId()).getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.without(deletedGroup)));
        }
    }
