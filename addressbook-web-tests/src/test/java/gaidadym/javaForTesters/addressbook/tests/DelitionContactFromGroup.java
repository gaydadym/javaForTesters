package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.ContactGroups;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;

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
    }

    @Test
    public void testDelitingContactFromGroup() throws Exception {
        ContactGroups contact = app.db().groupsForAllContacts().iterator().next();
        ContactGroups beforeGroups;
        ContactGroups afterGroups;
        beforeGroups = app.db().groupsForContact(false,contact.getId());
        app.contact().deleteContactFromGroup(contact);
        afterGroups = app.db().groupsForContact(false,contact.getId());
        assert !(beforeGroups.equals(afterGroups));
        }
    }
