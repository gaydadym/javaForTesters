package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
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
        ContactData contact = app.db().contacts(false).iterator().next();
        Groups beforeGroups = ((ContactData) contact).getGroups();
        app.contact().deleteContactFromGroup(contact);
        Groups afterGroups = ((ContactData) contact).getGroups();
        assert (beforeGroups.size()-afterGroups.size()==1);
        }
    }
