package gaidadym.javaForTesters.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
    public void gotoMainPage() {click(By.cssSelector("img[id = 'logo']"));}

    public void closeAlertWindow() {
        wd.switchTo().alert().accept();
    }
}
