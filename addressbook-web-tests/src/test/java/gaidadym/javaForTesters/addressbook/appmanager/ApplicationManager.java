package gaidadym.javaForTesters.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {


    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
    }


    public void init() {
        if (browser.equals(BrowserType.CHROME)){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--kiosk");
            wd = new ChromeDriver(options);
        }
        else {
            wd = new FirefoxDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        wd.get("http://localhost/addressbook");
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {

        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}

