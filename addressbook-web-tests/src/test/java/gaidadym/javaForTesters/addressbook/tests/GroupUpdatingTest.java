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
        if (! app.getGroupHelper().isThereGroup()){
            app.getGroupHelper().createGroup(new GroupData("new_group", "new_group", "new_group"));
        }
    }

    @Test
    public void testGroupUpdating() throws Exception {
        GroupData group = new GroupData("new_group", "new_group", "new_group");
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1;
        app.getGroupHelper().UpdateGroup(group, index);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }


}
