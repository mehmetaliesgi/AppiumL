package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screens.MainScreen;
import utils.helpers.ExcelReader;

import static utils.constants.TestGroups.*;

public class LoginExcelDataProviderTest extends BaseTestParallelTest {

    @Test(groups = {SMOKE},dataProvider = "parallelData")
    public void testLoginWithExcelDataRunParallel(String email, String password) {
        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
            .enterEmail(email)
            .enterPassword(password)
            .clickLoginForWrongInputs()
            .alertBtnOK()
            .clickBack();
    }

    @DataProvider(name = "parallelData", parallel = true)
    public Object[][] getExcelLoginDataAndRunParallel() {
        ExcelReader reader = new ExcelReader(
                "src/test/resources/testdata.xlsx",
                "ParallelLoginData"
        );

        Object[][] data = reader.getAllData();
        reader.close();

        return data;
    }

    @Test(groups = {REGRESSION}, dataProvider = "parallelData2")
    public void testLoginWithExcelDataRunParallel2(String email, String password) {
        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickMyAccount()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginForWrongInputs()
                .alertBtnOK()
                .clickBack();
    }

    @DataProvider(name = "parallelData2", parallel = true)
    public Object[][] getExcelLoginDataAndRunParallel2() {
        ExcelReader reader = new ExcelReader(
                "src/test/resources/testdata.xlsx",
                "ParallelLoginData2"
        );

        Object[][] data = reader.getAllData();
        reader.close();

        return data;
    }
}
