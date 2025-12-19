package screens;

import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.helpers.ConfigReader;

import java.time.Duration;
import java.util.Collections;

public class BaseScreen {
    protected AppiumDriver driver;
    private final long DEFAULT_TIMEOUT = ConfigReader.getExplicitWait();
    private final long POLLING_INTERVAL = ConfigReader.getPoolingInterval();

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)), this);
    }

    // Waits
    protected WebElement waitForVisibility(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisibility(WebElement element) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(WebElement element) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    // Clicks
    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    protected void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        waitForClickable(element);
        actions.moveToElement(element).doubleClick().perform();
    }

    // Send String
    protected void sendContext(WebElement element, String text) {
        waitForClickable(element);
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
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                    .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
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