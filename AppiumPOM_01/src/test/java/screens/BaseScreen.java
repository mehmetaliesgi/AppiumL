package screens;

import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class BaseScreen {
    protected AppiumDriver driver;
    private final long DEFAULT_TIMEOUT = 10;

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

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // Gesture
    protected void tap(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(700)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    protected void longPress(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) //waiting for 2 seconds for longPress on the Element
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

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

    protected void swipeUntilVisible(By locator, int maxSwipeCount, Direction direction) {
        int attempts = 0;

        while (attempts < maxSwipeCount) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) { }

            performSwipe(direction);
            attempts++;
        }

        throw new RuntimeException("Element not found after swiping: " + locator.toString());
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

    private void performSwipe(Direction direction) {
        Dimension size = driver.manage().window().getSize();

        int y = size.getHeight() / 2;
        int startX, endX;

        switch (direction) {
            case LEFT:
                startX = (int) (size.getWidth() * 0.8);
                endX = (int) (size.getWidth() * 0.2);
                break;
            case RIGHT:
                startX = (int) (size.getWidth() * 0.2);
                endX = (int) (size.getWidth() * 0.8);
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence seq = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(seq));
    }

    protected void dragAndDrop(WebElement elementSource, WebElement elementDestination) {
        Point sourceElementCenter = getCenterOfElement(elementSource.getLocation(), elementSource.getSize());
        Point targetElementCenter = getCenterOfElement(elementDestination.getLocation(), elementDestination.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(588)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(588), PointerInput.Origin.viewport(), targetElementCenter))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    protected void pinchOrZoom(WebElement element) {
        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(288)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(208), PointerInput.Origin.viewport(), centerOfElement.getX() + 100, centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() - 100, centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence, sequence2));
    }

    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.width / 2, location.getY() + size.height / 2);
    }
}
