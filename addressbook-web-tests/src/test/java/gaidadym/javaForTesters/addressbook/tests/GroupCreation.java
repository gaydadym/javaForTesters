package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreation extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupCreation() throws Exception {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test2").withHeader("test2").withFooter("test2");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        before.add(after.get(after.size()-1));
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }





}
