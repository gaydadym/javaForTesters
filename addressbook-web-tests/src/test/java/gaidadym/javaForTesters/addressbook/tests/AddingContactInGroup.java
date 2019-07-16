package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.ContactGroups;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.util.Set;

public class AddingContactInGroup extends TestBase {
    @Test
    public void testAddingContactInGroup() throws Exception {


        Set<ContactData> before = app.db().contacts(false);
        ContactData addedContact = before.iterator().next();
        ContactGroups beforeGroups;
        ContactGroups afterGroups;
        if (app.contact().freeGroupsForContact(addedContact).size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test"));
            app.goTo().mainPage();
        }

        beforeGroups = app.db().groupsForContact(false,addedContact.getId());
        app.contact().addContactInGroup(addedContact);
        afterGroups = app.db().groupsForContact(false,addedContact.getId());
        assert !(beforeGroups.equals(afterGroups));
        }
    }
