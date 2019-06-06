package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.linkText("add new")).click();
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getPhone());
        type(By.name("email"),contactData.getEmail());

    }
    public void submitContactCreation(){
        click(By.cssSelector("input[type = submit][name = submit]"));
    }

    public void viewContactDetails() {
        click(By.cssSelector("img[title = 'Details']"));
    }

    public void clickModifiy() {
        click(By.cssSelector("input[type = 'submit'][name = 'modifiy']"));
    }

    public void clickDelete() {
        click(By.cssSelector("input[type = 'submit'][value = 'Delete']"));
    }

    public void clickUpdate() {
        click(By.cssSelector("input[type = 'submit'][value = 'Enter']"));
    }

    public void closeAlertWindow() {
        wd.switchTo().alert().accept();
    }



}
