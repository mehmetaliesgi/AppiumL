package utils.listeners;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import tests.BaseTestParallelTest;
import utils.helpers.LogHelper;
import utils.helpers.ScreenshotHelper;

public class TestListener implements ITestListener {
    private static final Logger logger = LogHelper.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Başarısız: " + result.getMethod().getMethodName());
        logger.error("Hata Mesajı: " + result.getThrowable().getMessage());

        // Driver'ı test sınıfından al
        Object testClass = result.getInstance();
        AppiumDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            ScreenshotHelper screenshotHelper = new ScreenshotHelper(driver);

            // Test sınıfı ve metod adını al
            String className = result.getTestClass().getName();
            String methodName = result.getName();

            // Screenshot al
            String screenshotPath = screenshotHelper.takeOrganizedScreenshot(
                    className.substring(className.lastIndexOf('.') + 1),
                    methodName
            );

            logger.warn("Hata screenshot'ı kaydedildi: " + screenshotPath);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Başarılı: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(">>> Test Başladı: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Atlandı: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite BAŞLADI: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite BİTTİ: " + context.getName());
    }
}
