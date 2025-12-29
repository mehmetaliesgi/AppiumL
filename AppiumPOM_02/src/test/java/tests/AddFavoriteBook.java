package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import screens.MainScreen;
import screens.MyAccountScreen;

public class AddFavoriteBook extends BaseTest {

    @Test(groups = {"regression", "favorite"})
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

    @Test(dataProvider = "loginHardCoded")
    public void TryAddFavoriteWithLogin(String email, String password){
        loginAccount(email, password)
                .clickBtnHome()
                .clickCampaigns()
                .clickBook()
                .scrollDinaAndClickDino()
                .assertProductName("Dinozorlar Kitabı (Bez Çanta Hediyeli)")
                .scrollAddFavorite()
                .clickAddFavorite()
                .assertPreparationDesc("Ürün başarıyla favorilerinize eklendi.");
    }

    private MyAccountScreen loginAccount(String email, String password) {
        MainScreen mainScreen = new MainScreen(driver);

        return mainScreen.clickMyAccount()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();
    }

    @DataProvider(name = "loginHardCoded")
    public Object[][] loginHardCoded() {
        return new Object[][] {
                {"YOUR_MAIL", "YOUR_ADDRESS"}
        };
    }
}
