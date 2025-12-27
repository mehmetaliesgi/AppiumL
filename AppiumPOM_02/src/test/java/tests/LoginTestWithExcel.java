package tests;

import org.testng.annotations.Test;
import screens.MainScreen;
import utils.helpers.ExcelReader;

public class LoginTestWithExcel extends BaseTest{

    @Test
    public void loginTest() {
        ExcelReader reader = new ExcelReader(
                "src/test/resources/testdata.xlsx",
                "LoginData"
        );

        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
                .enterEmail(reader.getCellData(1, "Email"))
                .enterPassword(reader.getCellData(1, "Password"))
                .clickLogin()
                .verifyMyAccountScreen(reader.getCellData(1, "Email"));
    }
}
