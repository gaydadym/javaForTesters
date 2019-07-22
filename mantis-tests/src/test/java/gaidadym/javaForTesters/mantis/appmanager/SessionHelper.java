package gaidadym.javaForTesters.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }


    public void login(String username, String password) {
        type(By.name("username"),username);
        click(By.cssSelector("input[type = 'submit']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[type = 'submit']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}
