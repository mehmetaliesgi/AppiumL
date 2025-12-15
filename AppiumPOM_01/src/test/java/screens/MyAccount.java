package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyAccount extends BaseScreen{
    public MyAccount(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By login = AppiumBy.androidUIAutomator("new UiSelector().text(\"Giriş Yap\")");




    // Actions
    public LoginScreen login() {
        WebElement element = driver.findElement(login);
        element.click();
        return new LoginScreen(driver);
    }
}
