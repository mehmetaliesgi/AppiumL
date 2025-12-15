package screens;

import enums.Direction;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductsScreen extends BaseScreen{
    public ProductsScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By byFilter = AppiumBy.id("com.dmall.mfandroid:id/listingFilterTV");
    private final By bySorting = AppiumBy.id("com.dmall.mfandroid:id/listingSortTV");
    private final By byIncreasingPrice = AppiumBy.androidUIAutomator("new UiSelector().text(\"Artan Fiyat\")");
    private final By byProduct = AppiumBy.androidUIAutomator("new UiSelector().text(\"Adidas Ig8168 Adıfom Adılette I Çocuk Sandalet Siyah\")");



    // Actions
    public FilterScreen filterButton() {
        WebElement element=driver.findElement(byFilter);
        click(element);
        return new FilterScreen(driver);
    }

    public ProductsScreen listingSortButton() {
        WebElement element=driver.findElement(bySorting);
        click(element);
        return this;
    }

    public ProductsScreen listingIncreasingPriceButton() {
        WebElement element=driver.findElement(byIncreasingPrice);
        click(element);
        return this;
    }

    public ProductDetailsScreen clickProduct() {
        scrollUntilVisible(byProduct, 3, Direction.DOWN);
        WebElement element=driver.findElement(byProduct);
        click(element);
        return new ProductDetailsScreen(driver);
    }

}
