package gaidadym.javaForTesters.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import gaidadym.javaForTesters.mantis.appmanager.ApplicationManager;
import gaidadym.javaForTesters.mantis.model.Issue;
import gaidadym.javaForTesters.mantis.model.Project;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload
                (new File("src/test/resources/config_inc.php"),"/config/config_inc.php","config/config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("/config/config_inc.php.bak","/config/config_inc.php");
        app.stop();
    }

    public static boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {

        Set<Project> projects = app.soap().getProjects();
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issue = mc.mc_issue_get("administrator","admin", BigInteger.valueOf(issueId));
        String m  = issue.getStatus().getName();
        if (m.equals("opened")){
            return true;
        }else {
            return false;
        }
        }
    }
