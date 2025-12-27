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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Listeners(TestListener.class)
public class BaseTestParallelTest {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ScreenshotHelper> screenshotHelper = new ThreadLocal<>();
    private static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    private static Map<Long, Integer> threadIdMap = new HashMap<>();
    private static AtomicInteger deviceCounter = new AtomicInteger(0);
    private static final int DEVICE_COUNT = 2;

    public AppiumDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        long currentThreadId = Thread.currentThread().getId();

        int threadId;
        synchronized (threadIdMap) {
            if (!threadIdMap.containsKey(currentThreadId)) {
                int deviceIndex = (deviceCounter.getAndIncrement() % DEVICE_COUNT) + 1;
                threadIdMap.put(currentThreadId, deviceIndex);
            }
            threadId = threadIdMap.get(currentThreadId);
        }

        cleanupPort(threadId);

        UiAutomator2Options caps = getDesiredCapabilities(threadId);

        AppiumDriverLocalService localService = new AppiumServiceBuilder()
                .withIPAddress(ConfigReader.getAppiumServerUrl(threadId))
                .usingPort(ConfigReader.getAppiumServerPort(threadId))
                .withArgument(() -> "--base-path", "/wd/hub")
                .build();

        localService.start();
        service.set(localService);

        AppiumDriver appiumDriver = new AndroidDriver(localService.getUrl(), caps);
        driver.set(appiumDriver);

        ScreenshotHelper helper = new ScreenshotHelper(appiumDriver);
        screenshotHelper.set(helper);

        appiumDriver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getIntProperty("implicit.wait"))
        );
    }

    private UiAutomator2Options getDesiredCapabilities(int threadId) {
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setPlatformName(ConfigReader.getAppiumPlatformName());
        caps.setPlatformVersion(ConfigReader.getProperty("appium.platform.version"));
        caps.setAppPackage(ConfigReader.getAndroidPackageName());
        caps.setAppActivity(ConfigReader.getAndroidActivityName());
        caps.setAutomationName(ConfigReader.getProperty("appium.automation.name"));

        caps.setUdid(ConfigReader.getDeviceUdid(threadId));

        caps.setNoReset(ConfigReader.getAppiumNoReset());
        caps.setFullReset(ConfigReader.getBooleanProperty("appium.full.reset"));

        caps.setSystemPort(ConfigReader.getSystemPort(threadId));

        caps.setNewCommandTimeout(Duration.ofSeconds(300));

        return caps;
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

        if (service.get() != null) {
            service.get().stop();
            service.remove();
        }

        if (screenshotHelper.get() != null) {
            screenshotHelper.remove();
        }
    }

    protected void takeScreenshot(String className, String methodName) {
        if (ConfigReader.getBooleanProperty("screenshot.enabled")) {
            screenshotHelper.get().takeOrganizedScreenshot(className, methodName);
        }
    }

    private void cleanupPort(int threadId) {
        try {
            String deviceUdid = ConfigReader.getDeviceUdid(threadId);

            Process process = Runtime.getRuntime().exec(
                    "adb -s " + deviceUdid + " forward --remove-all"
            );
            process.waitFor();

        } catch (Exception e) {
            System.out.println("Port temizleme hatası: " + e.getMessage());
        }
    }
}