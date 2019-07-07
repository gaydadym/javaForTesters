package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static gaidadym.javaForTesters.addressbook.TestBase.app;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }
    public void create(ContactData contactData){
        clickAddContact();
        fillContactForm(contactData,true);
        submitContactCreation();
        app.goTo().mainPage();
    }
    public void update(ContactData contact) {
        app.contact().clickEdit(contact.getId());
        app.contact().fillContactForm(contact,false);
        app.contact().clickUpdate();
        app.goTo().mainPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDelete();
        app.goTo().closeAlertWindow();
        app.goTo().mainPage();
    }

    public void submitContactCreation(){
        click(By.cssSelector("input[type = submit][name = submit]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();

    }

    public void clickEdit(int id) {
        wd.findElement(By.cssSelector("tr>input[value = '" +id+ "'], img[alt = 'Edit']")).click();

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

    public int getContactCount() {
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
            ContactData contact = new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address).withEmail(email).withPhone(phone);
            contacts.add(contact);
        }
        return (contacts);
    }
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element: elements){
            List <WebElement> data;
            data = element.findElements(By.cssSelector("td"));
            String firstname = data.get(2).getAttribute("innerText");
            String lastname = data.get(1).getAttribute("innerText");
            String address = data.get(3).getAttribute("innerText");
            String email = data.get(4).getAttribute("innerText");
            String phone = data.get(5).getAttribute("innerText");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).withAddress(address).withEmail(email).withPhone(phone);
            contacts.add(contact);
        }
        return (contacts);
    }


}
