package gaidadym.javaForTesters.addressbook.tests;

import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCareation();
        app.fillGroupForm(new GroupData("test4", "test5", "test6"));
        app.returnToGroupPage();
        app.wd.findElement(By.linkText("Logout")).click();
    }


}
