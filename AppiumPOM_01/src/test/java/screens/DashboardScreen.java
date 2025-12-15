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
}
