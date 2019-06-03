package gaidadym.javaForTesters.addressbook.appmanager;

import com.sun.javafx.binding.ExpressionHelperBase;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(ChromeDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCareation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }
}
