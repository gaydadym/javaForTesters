package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.*;
import gaidadym.javaForTesters.addressbook.tests.ContactPhoneTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

import static gaidadym.javaForTesters.addressbook.TestBase.app;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        Groups groups = app.db().groups(false);
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("nickname"), contactData.getNickname());
        attach(By.name("photo"),contactData.getPhoto());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("title"),contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomePage());


        if (creation){
            if (contactData.getGroups().size()>0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(app.db().groups(false).iterator().next().getName());
            }
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

}
    public void create(ContactData contactData){
        clickAddContact();
        fillContactForm(contactData,true);
        submitContactCreation();
        contactCache = null;
        app.goTo().mainPage();
    }
    public void update(ContactData contact) {
        app.contact().clickEdit(contact.getId());
        app.contact().fillContactForm(contact,false);
        app.contact().clickUpdate();
        contactCache = null;
        app.goTo().mainPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDelete();
        app.goTo().closeAlertWindow();
        contactCache = null;
        app.goTo().mainPage();
    }

    public void submitContactCreation(){
        click(By.cssSelector("input[type = submit][name = submit]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();

    }

    public void clickEdit(int id) {
        wd.findElement(By.cssSelector("a[href = 'edit.php?id=" +id+ "']")).click();
        //wd.findElement(By.cssSelector("tr>input[value = '" +id+ "'], img[alt = 'Edit']")).click();

    }

    public void clickModifiy() {
        click(By.cssSelector("input[type = 'submit'][name = 'modifiy']"));
    }

    public void clickDelete() {
        click(By.cssSelector("input[type = 'button'][value = 'Delete']"));
    }

    public void clickUpdate() {
        click(By.cssSelector("input[type = 'submit'][value = 'Update']"));
    }

    public void clickAddContact() {
        click(By.linkText("add new"));
    }

    public boolean isThereContact() {
        return isElementPresent(By.cssSelector("input[name = 'selected[]']"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("input[type = 'checkbox'][name = 'selected[]']")).size();
    }
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element: elements){
            List <WebElement> data = new ArrayList<>();
            data = element.findElements(By.cssSelector("td"));
            String firstname = data.get(2).getAttribute("innerText");
            String lastname = data.get(1).getAttribute("innerText");
            String address = data.get(3).getAttribute("innerText");
            String email = data.get(4).getAttribute("innerText");
            String phone = data.get(5).getAttribute("innerText");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address).withEmail(email).withAllPhones(phone);
            contacts.add(contact);
        }
        return (contacts);
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache !=null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element: elements){
            List <WebElement> data;
            data = element.findElements(By.cssSelector("td"));
            String firstname = data.get(2).getAttribute("innerText");
            String lastname = data.get(1).getAttribute("innerText");
            String address = data.get(3).getAttribute("innerText");
            String allEmails = data.get(4).getAttribute("innerText");
            String allPhones = data.get(5).getAttribute("innerText");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address)
                    .withAllEmails(allEmails).withAllPhones(allPhones);
            contactCache.add(contact);
        }
        return (contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        clickEdit(contact.getId());
        String firstName = wd.findElement(By.cssSelector("input[name = 'firstname']")).getAttribute("value");
        String midName = wd.findElement(By.cssSelector("input[name = 'middlename']")).getAttribute("value");
        String lastName = wd.findElement(By.cssSelector("input[name = 'lastname']")).getAttribute("value");
        String nickName = wd.findElement(By.cssSelector("input[name = 'nickname']")).getAttribute("value");
        String company = wd.findElement(By.cssSelector("input[name = 'company']")).getAttribute("value");
        String title = wd.findElement(By.cssSelector("input[name = 'title']")).getAttribute("value");
        String address = wd.findElement(By.cssSelector("textarea[name = 'address']")).getAttribute("value");
        String homePhone = wd.findElement(By.cssSelector("input[name = 'home']")).getAttribute("value");
        String mobilePhone = wd.findElement(By.cssSelector("input[name = 'mobile']")).getAttribute("value");
        String workPhone = wd.findElement(By.cssSelector("input[name = 'work']")).getAttribute("value");
        String faxPhone = wd.findElement(By.cssSelector("input[name = 'fax']")).getAttribute("value");
        String email = wd.findElement(By.cssSelector("input[name = 'email']")).getAttribute("value");
        String email2 = wd.findElement(By.cssSelector("input[name = 'email2']")).getAttribute("value");
        String email3 = wd.findElement(By.cssSelector("input[name = 'email3']")).getAttribute("value");
        String homePage = wd.findElement(By.cssSelector("input[name = 'homepage']")).getAttribute("value");
        ContactData contactInformationFromEditForm = new ContactData().withFirstname(firstName).withMiddlename(midName).withLastname(lastName)
                .withNickname(nickName).withCompany(company).withTitle(title).withAddress(address).withHomePhone(homePhone)
                .withMobilePhone(mobilePhone).withWorkPhone(workPhone).withFaxPhone(faxPhone).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withHomePage(homePage);
        return contactInformationFromEditForm;


    }
    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s)-> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)-> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public void addContactInGroup(ContactData contact, GroupData group){
        selectContactById(contact.getId());
        selectGroupInDropList(group.getId());
        clickAdd();

    }

    public void deleteContactFromGroup(ContactData contact, GroupData group){
        clickDetails(contact.getId());
        clickOnGroupLink(group.getId());
        selectContactById(contact.getId());
        click(By.cssSelector("input[name = 'remove']"));
    }

    private void clickOnGroupLink(int id) {
        click(By.cssSelector(String.format("a[href = './index.php?group=%s']",id)));
    }

    private void clickDetails(int id) {
        click(By.cssSelector(String.format("a[href = 'view.php?id=%s']",id)));
    }

    private void clickAdd() {
        click(By.cssSelector("input[name = 'add']"));
    }

    private void selectGroupInDropList(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));


    }

    public Groups freeGroupsForContact(ContactData contact) {
        Set<GroupData> groupsOfContact = new HashSet<GroupData>();
        groupsOfContact = contact.getGroups();
        Set<GroupData> allGroups = new HashSet<GroupData>();
        allGroups = app.db().groups(false);
        Groups freeGroupsforContact = new Groups();
        for (GroupData group : allGroups) {
            if (!groupsOfContact.contains(group)) {
                    freeGroupsforContact.add(group);
                }
            }

            if(freeGroupsforContact.size()>0){
                GroupData createdGroup = new GroupData().withName("Created_group");
                app.goTo().groupPage();
                app.group().create(createdGroup);
                app.goTo().mainPage();
                allGroups = app.db().groups(false);
                for (GroupData group : allGroups) {
                    if (!groupsOfContact.contains(group)) {
                        freeGroupsforContact.add(group);
                    }
                }

            }

        return freeGroupsforContact;
    }
}
