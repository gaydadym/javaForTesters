package gaidadym.javaForTesters.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {

    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.wd;
    }

    public void start(String username, String email) {
    }
}
