package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.MainScreen;

public class SearchTest extends BaseTest {

    @Test
    public void searchTest(){
        String author = "Bertrand Russell";

        MainScreen mainScreen = new MainScreen(getDriver());

        mainScreen.clickSearch()
                .clickInputSearch()
                .enterInputSearch(author)
                .clickTextSearch()
                .getTitleAndVerify();

    }
}
