package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.mobisoft.kitapyurdu:id/inputEditText\").instance(0)")
    public WebElement inputEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.mobisoft.kitapyurdu:id/inputEditText\").instance(1)")
    public WebElement inputPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Giriş Yap\")")
    public WebElement btnLogin;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Ücretsiz Üye Ol\")")
    public WebElement btnRegister;


    // Actions
    public LoginScreen enterEmail(String email){
        sendContext(inputEmail, email);
        return this;
    }

    public LoginScreen enterPassword(String password){
        sendContext(inputPassword, password);
        return this;
    }

    public MyAccountScreen clickLogin(){
        click(btnLogin);
        return new MyAccountScreen(driver);
    }

    public RegisterScreen clickRegister(){
        click(btnRegister);
        return new RegisterScreen(driver);
    }
}
