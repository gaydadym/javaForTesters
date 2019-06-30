package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupDelitionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDelition() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        before.remove(1);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }


}
