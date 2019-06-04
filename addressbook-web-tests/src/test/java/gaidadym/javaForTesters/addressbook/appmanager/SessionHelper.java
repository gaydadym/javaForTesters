package gaidadym.javaForTesters.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(ChromeDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        type(By.name("user"),username);
        type(By.name("pass"),password);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}
