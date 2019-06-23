package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
    public void createContact (ContactData contactData){
        clickAddContact();
        fillContactForm(contactData,true);
        submitContactCreation();


    }
    public void submitContactCreation(){
        click(By.cssSelector("input[type = submit][name = submit]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("input[name = 'selected[]']")).get(index).click();
    }
    public void viewContactDetails(int index) {
        wd.findElements(By.cssSelector("img[title = 'Details']")).get(index).click();
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
}
