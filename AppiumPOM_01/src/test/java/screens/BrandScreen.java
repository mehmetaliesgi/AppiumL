package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BrandScreen extends BaseScreen{
    public BrandScreen(AppiumDriver driver) {
        super(driver);
    }

    // Locators
    private final By bySearchInput = AppiumBy.id("com.dmall.mfandroid:id/et_attribute_search_bar");
    private final By byBrand = AppiumBy.id("com.dmall.mfandroid:id/nameTV");
    private final By byApply = AppiumBy.id("com.dmall.mfandroid:id/tv_name");

    // Actions
    public BrandScreen searchBrand(String brand){
        WebElement element = driver.findElement(bySearchInput);
        type(element, brand);
        return this;
    }

    public BrandScreen clickBrand(){
        WebElement element = driver.findElement(byBrand);
        click(element);
        return this;
    }

    public FilterScreen clickApply(){
        WebElement element = driver.findElement(byApply);
        click(element);
        return new FilterScreen(driver);
    }

}
