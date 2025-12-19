package tests;

import org.testng.annotations.Test;
import screens.MainScreen;
import utils.helpers.ConfigReader;

public class LoginTest extends BaseTest{

    @Test
    public void successfulLoginTest() {
        MainScreen mainScreen = new MainScreen(driver);

        mainScreen.clickMyAccount()
                .enterEmail(ConfigReader.getTestUserEmail())
                .enterPassword(ConfigReader.getTestUserPassword())
                .clickLogin()
                .verifyMyAccountScreen(ConfigReader.getTestUserEmail());
    }
}
