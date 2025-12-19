package tests;

import org.testng.annotations.Test;
import screens.MainScreen;

public class LoginTestWithExcel extends BaseTest{

    @Test
    public void loginTest() {
        MainScreen mainScreen = new MainScreen(driver);

        mainScreen.clickMyAccount()
                .enterEmail(reader.getCellData(1, "Email"))
                .enterPassword(reader.getCellData(1, "Password"))
                .clickLogin()
                .verifyMyAccountScreen(reader.getCellData(1, "Email"));
    }
}
