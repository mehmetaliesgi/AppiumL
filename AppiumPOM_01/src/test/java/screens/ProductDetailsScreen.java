package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsScreen extends BaseScreen{
    public ProductDetailsScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By byAddToCart = AppiumBy.id("com.dmall.mfandroid:id/pdpAddToCartButton");




    // Actions
    public CartScreen clickAddToCart(){
        WebElement element = driver.findElement(byAddToCart);
        click(element);
        return new  CartScreen(driver);
    }


}
