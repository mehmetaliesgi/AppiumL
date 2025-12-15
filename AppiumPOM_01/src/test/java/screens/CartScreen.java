package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CartScreen extends BaseScreen{
    public CartScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By byCartTitle = AppiumBy.id("com.dmall.mfandroid:id/tvBasketTitle");




    // Actions
    public CartScreen checkCart(String cartTitle) {
        WebElement element = driver.findElement(byCartTitle);
        String actualTitle = element.getText();
        Assert.assertEquals(actualTitle, cartTitle, "Cart title is incorrect.");
        return this;
    }
}
