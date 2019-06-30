package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupUpdatingTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.group().list().size()==0){
            app.group().create(new GroupData("new_group", "new_group", "new_group"));
        }
    }

    @Test
    public void testGroupUpdating() throws Exception {
        GroupData group = new GroupData("new_group", "new_group", "new_group");
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().update(group, index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }


}
