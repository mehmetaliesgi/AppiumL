package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.helpers.ConfigReader;
import utils.helpers.ScreenshotHelper;
import utils.listeners.TestListener;

import java.time.Duration;


@Listeners(TestListener.class)
public class BaseTest {
    public AppiumDriver driver;
    protected ScreenshotHelper screenshotHelper;

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() throws Exception {
        UiAutomator2Options caps = getDesiredCapabilities();

        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withArgument(() -> "--base-path", "/wd/hub")
                .build();
        service.start();

        driver = new AndroidDriver(service.getUrl(), caps);

        screenshotHelper = new ScreenshotHelper(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getIntProperty("implicit.wait")));

        System.out.println("Test ortamı hazır: " + ConfigReader.getProperty("environment"));
    }

    private UiAutomator2Options getDesiredCapabilities() {
        UiAutomator2Options  caps = new UiAutomator2Options ();
        caps.setCapability("platformName", ConfigReader.getAppiumPlatformName());
        caps.setCapability("appium:platformVersion", ConfigReader.getProperty("appium.platform.version"));
        caps.setCapability("appium:packageName", ConfigReader.getAndroidPackageName());
        caps.setCapability("appium:activityName", ConfigReader.getAndroidActivityName());
        caps.setCapability("appium:automationName", ConfigReader.getProperty("appium.automation.name"));
        caps.setCapability("appium:noReset", ConfigReader.getAppiumNoReset());
        caps.setCapability("appium:fullReset", ConfigReader.getBooleanProperty("appium.full.reset"));

        return caps;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void takeScreenshot(String className, String methodName) {
        if (ConfigReader.getBooleanProperty("screenshot.enabled")) {
            screenshotHelper.takeOrganizedScreenshot(className, methodName);
        }
    }
}