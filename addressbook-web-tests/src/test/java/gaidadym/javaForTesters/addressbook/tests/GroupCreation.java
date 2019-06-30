package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("test4", "test5", "test6");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        before.add(after.get(after.size()-1));
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }





}
