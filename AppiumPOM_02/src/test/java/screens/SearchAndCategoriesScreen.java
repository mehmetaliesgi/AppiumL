package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SearchAndCategoriesScreen extends BaseScreen {
    public SearchAndCategoriesScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(accessibility = "kitap, yazar, yayınevi ara")
    public WebElement inputSearch;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"bertrand russell\")")
    public WebElement textSearch;


    // Actions
    public SearchAndCategoriesScreen clickInputSearch(){
        click(inputSearch);
        return this;
    }

    public SearchAndCategoriesScreen enterInputSearch(String value){
        sendContext(inputSearch, value);
        return this;
    }

    public SearchResultScreen clickTextSearch(){
        click(textSearch);
        return new SearchResultScreen(driver);
    }

}
