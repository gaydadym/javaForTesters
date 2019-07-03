package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupUpdatingTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("new group").withFooter("new group").withHeader("new hroup"));
        }
    }

    @Test
    public void testGroupUpdating() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData updatedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(updatedGroup.getId()).withName("Updated");
        app.group().update(group);
        Set<GroupData> after = app.group().all();
        group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
        Assert.assertEquals(after.size(),before.size());
    }


}
