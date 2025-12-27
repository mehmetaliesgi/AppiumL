package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SearchResultScreen extends BaseScreen{
    public SearchResultScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(id = "com.mobisoft.kitapyurdu:id/back_button_center_text")
    public WebElement title;

    // Actions
    public SearchResultScreen getTitleAndVerify(){
        String expectedTitle = "Arama sonuçları bertrand russell";

        String actualEmail = getText(title);
        Assert.assertEquals(actualEmail, expectedTitle, "Your search result is incorrect.");
        return this;
    }
}
