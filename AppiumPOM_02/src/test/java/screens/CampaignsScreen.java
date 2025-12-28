package screens;

import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CampaignsScreen extends BaseScreen {
    public CampaignsScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Kitap\")")
    public WebElement elBook;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Dinozorlar kitabını alana Askılı Bez Çanta Hediye...\")")
    public WebElement elDino;



    // Actions
    public CampaignsScreen clickBook() {
        click(elBook);
        return this;
    }

    public BookDetailScreen scrollDinaAndClickDino() {
        scrollUntilVisibleWithWebElement(elDino, 10, Direction.DOWN);
        System.out.println("-----------DINO BULUNDU VE TIKLANDI-----------------------");
        click(elDino);
        return new BookDetailScreen(driver);
    }
}
