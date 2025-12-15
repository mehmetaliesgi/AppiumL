package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By usernameInput = AppiumBy.id("com.dmall.mfandroid:id/text_input_edit_text");
    private final By loginButton = AppiumBy.id("com.dmall.mfandroid:id/tv_name");
    private final By passwordInput = AppiumBy.accessibilityId("et_password");
    private final By errorMessageText =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"E-posta adresi veya şifre hatalı, kontrol edebilir misin?\")"
            );


    // Actions
    public LoginScreen enterUsername(String username){
        WebElement usernameEl = driver.findElement(usernameInput);
        click(usernameEl);
        type(usernameEl, username);
        return this;
    }

    public LoginScreen clickLoginButton(){
        WebElement button = driver.findElement(loginButton);
        click(button);
        return this;
    }

    public LoginScreen enterPassword(String password){
        WebElement passwordEl = driver.findElement(passwordInput);
        type(passwordEl, password);
        return this;
    }

    public LoginScreen enterErrorMessage(String errorMessage){
        WebElement element = driver.findElement(errorMessageText);
        String actualString = element.getAttribute("name");
        Assert.assertEquals(errorMessage, actualString);
        return this;
    }
}
