package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FilterScreen extends BaseScreen{
    public FilterScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By byBrand = AppiumBy.androidUIAutomator("new UiSelector().text(\"Marka\")");
    private final By byMinPriceInput = AppiumBy.id("com.dmall.mfandroid:id/minPriceET");
    private final By byMaxPriceInput = AppiumBy.id("com.dmall.mfandroid:id/maxPriceET");
    private final By byStoreRating =  AppiumBy.id("com.dmall.mfandroid:id/tvThirdSellerGrade");
    private final By byShowResults =  AppiumBy.id("com.dmall.mfandroid:id/giybiFilterShowResultsBTN");


    // Actions
    public BrandScreen clickBrand(){
        WebElement element = driver.findElement(byBrand);
        click(element);
        return new BrandScreen(driver);
    }

    public FilterScreen enterMinPrice(String minPrice){
        WebElement element = driver.findElement(byMinPriceInput);
        type(element, minPrice);
        return this;
    }

    public FilterScreen enterMaxPrice(String maxPrice){
        WebElement element = driver.findElement(byMaxPriceInput);
        type(element, maxPrice);
        return this;
    }

    public FilterScreen clickStoreRating(){
        WebElement element = driver.findElement(byStoreRating);
        click(element);
        return this;
    }

    public ProductsScreen clickShowResults(){
        WebElement element = driver.findElement(byShowResults);
        click(element);
        return new ProductsScreen(driver);
    }
}
