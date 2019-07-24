package gaidadym.javaForTesters.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import gaidadym.javaForTesters.mantis.model.Issue;
import gaidadym.javaForTesters.mantis.model.Project;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static gaidadym.javaForTesters.mantis.tests.TestBase.app;
import static gaidadym.javaForTesters.mantis.tests.TestBase.isIssueOpen;

public class SoapTests {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for(Project project: projects){
            project.getName();
        }

    }

    @Test
    public void testCreateIssue()throws MalformedURLException, ServiceException, RemoteException{
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);
        Assert.assertEquals(createdIssue.getSummary(),issue.getSummary());
        boolean tr = isIssueOpen(6);
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
