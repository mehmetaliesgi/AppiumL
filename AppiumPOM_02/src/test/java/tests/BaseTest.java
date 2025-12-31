package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.helpers.ConfigReader;
import utils.helpers.LogHelper;
import utils.helpers.ScreenshotHelper;
import utils.listeners.TestListener;

import java.time.Duration;


@Listeners(TestListener.class)
public class BaseTest {
    public AppiumDriver driver;
    protected ScreenshotHelper screenshotHelper;
    protected static final Logger logger = LogHelper.getLogger(BaseTest.class);

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        try {
            logger.info("Test ortamı hazırlanıyor...");
            UiAutomator2Options caps = getDesiredCapabilities();

            AppiumDriverLocalService service = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(() -> "--base-path", "/wd/hub")
                    .build();
            service.start();

            long startTime = System.currentTimeMillis();

            driver = new AndroidDriver(service.getUrl(), caps);

            long endTime = System.currentTimeMillis();
            logger.info("Driver başlatıldı - Süre: {}ms", (endTime - startTime));

            screenshotHelper = new ScreenshotHelper(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getIntProperty("implicit.wait")));

            logger.info("Test ortamı hazır: " + ConfigReader.getProperty("environment"));
        } catch (Exception ex) {
            LogHelper.logException(logger, "Driver başlatılamadı", ex);
            throw ex;
        }
    }

    private UiAutomator2Options getDesiredCapabilities() {
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setCapability("platformName", ConfigReader.getAppiumPlatformName());
        caps.setCapability("appium:platformVersion", ConfigReader.getProperty("appium.platform.version"));
        caps.setCapability("appium:packageName", ConfigReader.getAndroidPackageName());
        caps.setCapability("appium:activityName", ConfigReader.getAndroidActivityName());
        caps.setCapability("appium:automationName", ConfigReader.getProperty("appium.automation.name"));
        caps.setCapability("appium:noReset", ConfigReader.getAppiumNoReset());
        caps.setCapability("appium:fullReset", ConfigReader.getBooleanProperty("appium.full.reset"));

        String appiumServerUrl = "http://127.0.0.1:" + 4723 + "/wd/hub";
        logger.info("Appium Server'a bağlanılıyor: {}", appiumServerUrl);

        return caps;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result ) {
        if (driver != null) {
            try {
                boolean passed = result.getStatus() == ITestResult.SUCCESS;
                LogHelper.logTestEnd(logger, result.getName(), passed);

                logger.info("Driver kapatılıyor");
                driver.quit();

                logger.info("Cleanup tamamlandı");
            } catch (Exception e) {
                LogHelper.logException(logger, "TearDown hatası", e);
            }
        }
    }

    protected void takeScreenshot(String className, String methodName) {
        if (ConfigReader.getBooleanProperty("screenshot.enabled")) {
            logger.debug("Screenshot alınıyor.....");
            screenshotHelper.takeOrganizedScreenshot(className, methodName);
        }
    }
}