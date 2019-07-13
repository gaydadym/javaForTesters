package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupUpdatingTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups(false).size() == 0) {
            app.group().create(new GroupData().withName("new group").withFooter("new group").withHeader("new hroup"));
        }
    }

    @Test
    public void testGroupUpdating() throws Exception {
        Groups before = app.db().groups(false);
        GroupData updatedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(updatedGroup.getId()).withName("Updated");
        app.group().update(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.db().groups(false);
        assertThat(after, equalTo(before.without(group).withAdded(updatedGroup)));
    }


}
