package gaidadym.javaForTesters.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper {
    private ChromeDriver wd;

    public NavigationHelper(ChromeDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        wd.get("http://localhost/addressbook/Group.php");
    }
}
