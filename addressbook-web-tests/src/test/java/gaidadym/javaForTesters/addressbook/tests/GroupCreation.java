package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.appmanager.SessionHelper;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCareation();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", "test6"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }




}
