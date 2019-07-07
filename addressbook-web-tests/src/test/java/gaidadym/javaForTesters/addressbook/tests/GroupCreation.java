package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreation extends TestBase {
    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2").withHeader("test2").withFooter("test2");
        app.group().create(group);
        Groups after = app.group().all();
        group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded
                (group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }





}
