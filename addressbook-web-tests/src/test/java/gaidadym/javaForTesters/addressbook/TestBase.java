package gaidadym.javaForTesters.addressbook;

import gaidadym.javaForTesters.addressbook.appmanager.ApplicationManager;
import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import gaidadym.javaForTesters.addressbook.tests.GroupCreation;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object [] p){
        logger.info("Start test "+m.getName()+" with parameters "+ Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object [] p){
        logger.info("Stop test "+m.getName()+" with parameters "+ Arrays.asList(p));
    }

    public ApplicationManager getApp() {
        return app;
    }

    public void verifyGroupListInUI(){
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups(false);
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g)-> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }

    }

    public void verifyContactListInUI(){
        if (Boolean.getBoolean("verifyUI")){
            Contacts dbContacts = app.db().contacts(false);
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((c)-> new ContactData().withId(c.getId())
                    .withLastname(c.getLastname()).withFirstname(c.getFirstname()).withAddress(c.getAddress())
            .withAllEmails(app.contact().mergeEmails(c)).withAllPhones(app.contact().mergePhones(c)))
                    .collect(Collectors.toSet())));
        }

    }
}
