package gaidadym.javaForTesters.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.HttpSessionId;

import javax.management.monitor.Monitor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;


    public WebDriver wd;
    public RegistrationHelper registration(){
        return new RegistrationHelper(this);
    }

    private String browser;


    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();

    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
        if (browser.equals(BrowserType.CHROME)){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--kiosk");
            wd = new ChromeDriver(options);
        }
        else {
            wd = new FirefoxDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        wd.get(properties.getProperty("web.baseUrl"));


    }
    public HttpSession newSession(){
        return new HttpSession(this);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }


    public void stop() {
        wd.quit();
    }







}

