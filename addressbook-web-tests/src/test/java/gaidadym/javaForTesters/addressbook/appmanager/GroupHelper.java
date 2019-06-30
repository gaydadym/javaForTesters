package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
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

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }
    public void clickUpdateGroup() {
        click(By.name("edit"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.cssSelector("input[name = 'selected[]']")).get(index).click();

    }

    public boolean isThereGroup() {
        return isElementPresent(By.cssSelector("input[name = 'selected[]']"));
    }

    public void create(GroupData groupData){
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void update(GroupData group, int index) {
        selectGroup(index);
        clickUpdateGroup();
        fillGroupForm(group);
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroups();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.cssSelector("input[type = 'checkbox'][name = 'selected[]']")).size();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements){
            String name = element.getText();
            GroupData group = new GroupData().withName(name);
            groups.add(group);
        }
        return groups;
    }
}
