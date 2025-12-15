package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.PopupUtils;

public class DashboardScreen extends BaseScreen{
    public DashboardScreen(AppiumDriver driver) {
        super(driver);
    }

    PopupUtils popupUtils  = new PopupUtils(driver);

    // Locators
    private final By notificationPermission = By.id("com.android.permissioncontroller:id/permission_deny_and_dont_ask_again_button");
    private final By todaysDeals = By.id("com.dmall.mfandroid:id/btnClose");
    private final By myAccount = AppiumBy.androidUIAutomator("new UiSelector().text(\"Hesabım\")");
    private final By bySearch = AppiumBy.id("com.dmall.mfandroid:id/tvHomeSearchBar");
    private final By bySearchInput = AppiumBy.id("com.dmall.mfandroid:id/etSearch");
    private final By byResultProduct = AppiumBy.androidUIAutomator("new UiSelector().text(\"Çocuk Ayakkabı\")");


    // Actions
    public DashboardScreen notificationAlert() {
        popupUtils.closeIfVisible(notificationPermission, 5);
        return this;
    }


    public DashboardScreen todaysDeals(){
        popupUtils.closeIfVisible(todaysDeals, 5);
        return this;
    }


    public MyAccount myAccount(){
        WebElement element=driver.findElement(myAccount);
        click(element);
        return new MyAccount(driver);
    }

    public DashboardScreen search(){
        WebElement element=driver.findElement(bySearch);
        click(element);
        return this;
    }

    public DashboardScreen searchInput(String product){
        WebElement element=driver.findElement(bySearchInput);
        type(element,product);
        return this;
    }

    public ProductsScreen resultProduct(){
        WebElement element=driver.findElement(byResultProduct);
        click(element);
        return new ProductsScreen(driver);
    }

}
