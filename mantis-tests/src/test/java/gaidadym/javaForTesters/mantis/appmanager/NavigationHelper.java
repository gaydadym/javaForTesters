package gaidadym.javaForTesters.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }
    public void mainPage() {click(By.cssSelector("img[id = 'logo']"));}

    public void closeAlertWindow() {
        wd.switchTo().alert().accept();
    }

    public void userManagePage() {
        click(By.linkText("Управление пользователями"));
    }

    public void selectUserById(int id) {
        click(By.cssSelector(String.format("a[href = 'manage_user_edit_page.php?user_id=%s",id)));
    }

    public void clickResetPassword() {
        click(By.cssSelector("input[type = 'submit'][value = 'Сбросить пароль']"));
    }
}
