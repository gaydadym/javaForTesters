package gaidadym.javaForTesters.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter (names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }


        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
            //} else if (format.equals("json")) {
            //saveAsJson(contacts, new File(file));
        } else System.out.println("Unrecognized format " + format);
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstname(), contact.getLastname(), contact.getMiddlename(), contact.getAddress(),
                        contact.getCompany(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                        contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getGroup(),contact.getPhoto()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count;i++){
            contacts.add(new ContactData()
                    .withFirstname(String.format("Firstname%s",i))
                    .withLastname(String.format("Lastname%s",i))
                    .withMiddlename(String.format("MiddleName%s",i))
                    .withAddress(String.format("Address%s",i))
                    .withCompany(String.format("Company%s",i))
                    .withEmail(String.format("Email@ya.ru%s",i))
                    .withEmail2(String.format("Email2@ya.ru%s",i))
                    .withEmail3(String.format("Email3@ya.ru%s",i))
                    .withHomePhone(String.format("1256423000%s",i))
                    .withMobilePhone(String.format("8950777889%s",i))
                    .withWorkPhone(String.format("111111%s",i))
                    .withGroup("test2")
                    .withPhoto(new File("src/test/resources/screen.jpg")));
        }
        return contacts;
    }
}