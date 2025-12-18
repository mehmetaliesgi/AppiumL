package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.ScreenshotHelper;
import utils.listeners.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {
    public AppiumDriver driver;
    protected ScreenshotHelper screenshotHelper;

    public  AppiumDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities caps = getDesiredCapabilities();

        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        driver = new AndroidDriver(service.getUrl(), caps);

        screenshotHelper = new ScreenshotHelper(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "16.0");
        caps.setCapability("appium:packageName", "com.mobisoft.kitapyurdu");
        caps.setCapability("appium:activityName", "com.mobisoft.kitapyurdu.main.MainActivity");
        caps.setCapability("appium:automationName", "UIAutomator2");
        caps.setCapability("appium:deviceName", "MediumPhone");
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:fullReset", false);
        return caps;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void takeScreenshot(String className, String methodName) {
        screenshotHelper.takeOrganizedScreenshot(className, methodName);
    }
}
