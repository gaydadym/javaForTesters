package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupDelitionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDelition() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        before.remove(deletedGroup);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }


}
