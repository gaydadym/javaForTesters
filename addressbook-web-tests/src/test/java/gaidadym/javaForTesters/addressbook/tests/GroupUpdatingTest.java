package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.TestBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupUpdatingTest extends TestBase {

    @Test
    public void testGroupUpdating() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().clickUpdateGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("Updated","Updated_group","Updated"));
        app.getGroupHelper().returnToGroupPage();
    }

}
