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

    @AndroidFindBy(accessibility = "Ara")
    public WebElement btnSearch;

    @AndroidFindBy(accessibility = "Kampanyalar")
    public WebElement btnCampaigns;


    // Actions
    public LoginScreen clickMyAccount(){
        click(btnMyAccount);
        return new LoginScreen(driver);
    }

    public SearchAndCategoriesScreen clickSearch(){
        click(btnSearch);
        return new SearchAndCategoriesScreen(driver);
    }

    public CampaignsScreen clickCampaigns() {
        click(btnCampaigns);
        return new CampaignsScreen(driver);
    }
}
