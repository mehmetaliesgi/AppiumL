package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screens.MainScreen;
import utils.helpers.ConfigReader;
import utils.helpers.ExcelReader;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {
        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
                .enterEmail(ConfigReader.getTestUserEmail())
                .enterPassword(ConfigReader.getTestUserPassword())
                .clickLogin()
                .verifyMyAccountScreen(ConfigReader.getTestUserEmail());
    }

    @Test(dataProvider = "loginData")
    public void invalidLoginWithDifferentDataTest(String email, String password){
        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginForWrongInputs()
                .alertBtnOK()
                .clickBack();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"wronguser@example.com", "Test123456"},
                {"testuser@example.com", "wrongpass"},
                {"", "Test123456"},
                {"testuser@example.com", ""}
        };
    }


    @Test(dataProvider = "excelLoginData")
    public void testLoginWithExcelData(String email, String password) {
        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginForWrongInputs()
                .alertBtnOK()
                .clickBack();
    }


    @DataProvider(name = "excelLoginData")
    public Object[][] getExcelLoginData() {
        ExcelReader reader = new ExcelReader(
                "src/test/resources/testdata.xlsx",
                "InvalidLoginData"
        );

        Object[][] data = reader.getAllData();
        reader.close();

        return data;
    }
}
