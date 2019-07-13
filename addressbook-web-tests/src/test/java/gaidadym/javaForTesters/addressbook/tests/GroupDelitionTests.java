package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDelitionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.db().groups(false).size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDelition() throws Exception {
        Groups before = app.db().groups(false);
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after = app.db().groups(false);
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }


}
