package gaidadym.javaForTesters.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {

        Contacts before = app.db().contacts(false);
        System.out.println(before);
        app.contact().create(contact);
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.db().contacts(false);
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
        verifyContactListInUI();

    }

}
