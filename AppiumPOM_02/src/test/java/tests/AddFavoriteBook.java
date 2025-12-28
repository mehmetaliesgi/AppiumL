package tests;

import org.testng.annotations.Test;
import screens.MainScreen;

public class AddFavoriteBook extends BaseTest {

    @Test
    public void TryAddFavoriteWithoutLogin(){
        MainScreen mainScreen = new MainScreen(driver);

        mainScreen.clickCampaigns()
                .clickBook()
                .scrollDinaAndClickDino()
                .assertProductName("Dinozorlar Kitabı (Bez Çanta Hediyeli)")
                .scrollAddFavorite()
                .clickAddFavorite()
                .clickCancelLogin()
                .clickMainScreen();
    }
}
