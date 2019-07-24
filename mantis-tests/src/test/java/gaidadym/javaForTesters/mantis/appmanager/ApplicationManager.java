package gaidadym.javaForTesters.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static gaidadym.javaForTesters.mantis.tests.TestBase.app;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;



    private String browser;
    private SessionHelper session;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new Properties();

    }


    public void init() throws IOException {
        dbHelper = new DbHelper();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));




    }
    public HttpSession newSession(){
        return new HttpSession(this);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }


    public void stop() {
        if (wd != null){
            wd.quit();
        }
    }


    public RegistrationHelper registration() {
        if (registrationHelper==null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver(){
        if(wd==null){
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
        return wd;
    }
    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    public DbHelper db() {
        return dbHelper;
    }

    public SessionHelper session() {
        if (session==null){
            session = new SessionHelper(this);
        }
        return session;
    }

    public NavigationHelper goTo() {
        if (navigationHelper==null){
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public SoapHelper soap(){
        if (soapHelper ==null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }


}

