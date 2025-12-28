package screens;

import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BookDetailScreen extends BaseScreen{
    public BookDetailScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(id = "com.mobisoft.kitapyurdu:id/textViewProductName")
    public WebElement elProductName;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Favorilerime Ekle\")")
    public WebElement elAddFavorite;

    @AndroidFindBy(accessibility = "Vazgeç")
    public WebElement elCancelLogin;

    @AndroidFindBy(accessibility = "Ana Sayfa")
    public WebElement elMainScreen;

    @AndroidFindBy(id = "com.mobisoft.kitapyurdu:id/textViewPreparationDesc")
    public WebElement elPreparationDesc;



    // Actions
    public BookDetailScreen assertProductName(String productName){
        String actualProductName = getText(elProductName);
        Assert.assertEquals(actualProductName, productName, "Product name is incorrect.");
        return this;
    }

    public BookDetailScreen scrollAddFavorite(){
        scrollUntilVisibleWithWebElement(elAddFavorite, 10, Direction.DOWN);
        return this;
    }

    public BookDetailScreen clickAddFavorite(){
        click(elAddFavorite);
        return this;
    }

    public BookDetailScreen clickCancelLogin(){
        click(elCancelLogin);
        return this;
    }

    public MainScreen clickMainScreen(){
        click(elMainScreen);
        return new MainScreen(driver);
    }

    public BookDetailScreen assertPreparationDesc(String preparationDesc){
        String actualMessage = getText(elPreparationDesc);
        Assert.assertEquals(actualMessage, preparationDesc, "Preparation desc is incorrect.");
        return this;
    }

}
