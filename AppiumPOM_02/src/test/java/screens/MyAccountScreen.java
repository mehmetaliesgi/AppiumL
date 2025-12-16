package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MyAccountScreen extends BaseScreen {
    public MyAccountScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    @AndroidFindBy(id = "com.mobisoft.kitapyurdu:id/textViewMail")
    public WebElement txtMail;


    // Actions
    public MyAccountScreen verifyMyAccountScreen(String expectedMmail) {
        String actualEmail = getText(txtMail);
        Assert.assertEquals(actualEmail, expectedMmail, "Login attempt failed");
        return this;
    }
}
