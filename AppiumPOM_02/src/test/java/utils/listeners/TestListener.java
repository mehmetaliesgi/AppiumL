package utils.listeners;

import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ScreenshotHelper;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test BAŞARISIZ: " + result.getName());

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

            System.out.println("Hata screenshot'ı kaydedildi: " + screenshotPath);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test BAŞARILI: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test BAŞLADI: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite BAŞLADI: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite BİTTİ: " + context.getName());
    }
}
