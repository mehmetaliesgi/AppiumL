package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainScreen extends BaseScreen{
    public MainScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(accessibility = "Hesabım")
    public WebElement btnMyAccount;

    // Actions
    public LoginScreen clickMyAccount(){
        click(btnMyAccount);
        return new LoginScreen(driver);
    }
}
