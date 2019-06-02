package gaidadym.javaForTesters.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupCreation extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCareation();
        fillGroupForm(new GroupData("test4", "test5", "test6"));
        returnToGroupPage();
        wd.findElement(By.linkText("Logout")).click();
    }


}
