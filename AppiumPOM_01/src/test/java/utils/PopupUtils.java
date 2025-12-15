package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopupUtils {

    private final AppiumDriver driver;

    public PopupUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeIfVisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

            WebElement popup =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            popup.click();
            System.out.println("Popup bulundu ve kapatıldı.");

        } catch (TimeoutException e) {
            System.out.println("Popup görünmedi, teste devam ediliyor.");
        }
    }

}
