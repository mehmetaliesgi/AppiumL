package tests;

import org.testng.annotations.Test;
import screens.MainScreen;

public class LoginTest extends BaseTest{

    @Test
    public void successfulLoginTest() {
        MainScreen mainScreen = new MainScreen(driver);

        mainScreen.clickMyAccount()
                .enterEmail("YOUR_EMAIL")
                .enterPassword("YOUR_PASSWORD")
                .clickLogin()
                .verifyMyAccountScreen("YOUR_EMAIL");
    }
}
