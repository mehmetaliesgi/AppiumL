package screens;

import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class BaseScreen {
    protected AppiumDriver driver;
    private final long DEFAULT_TIMEOUT = 3;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)), this);
    }

    // Waits
    protected WebElement waitForVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Clicks
    protected void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);

        waitForVisibility(element);
        actions.moveToElement(element).doubleClick().perform();
    }

    // Send String
    protected void sendContext(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // Get text
    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    // Display Element
    protected boolean isElementPresent(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(driver -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Scroll
    protected void scrollUntilVisible(By locator, int maxScrollCount, Direction direction) {
        int attempts = 0;

        while (attempts < maxScrollCount) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) {
            }

            performScroll(direction);
            attempts++;
        }

        throw new RuntimeException("Element not found after scrolling: " + locator.toString());
    }

    private void performScroll(Direction direction) {
        Dimension size = driver.manage().window().getSize();

        int startX = size.getWidth() / 2;
        int endX = startX;

        int startY, endY;

        switch (direction) {
            case DOWN:
                startY = (int) (size.getHeight() * 0.8);
                endY = (int) (size.getHeight() * 0.2);
                break;
            case UP:
                startY = (int) (size.getHeight() * 0.2);
                endY = (int) (size.getHeight() * 0.8);
                break;
            default:
                throw new IllegalArgumentException("Invalid scroll direction for performScroll: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence seq = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(seq));
    }
}
