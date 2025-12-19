package utils.helpers;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    private AppiumDriver driver;

    public ScreenshotHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    public String takeOrganizedScreenshot(String testClassName, String testMethodName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String folderPath = ConfigReader.getProperty("screenshot.path") + testClassName;
            String fileName = testMethodName + "_" + timestamp + ".png";
            String fullPath = folderPath + "/" + fileName;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fullPath);

            destFile.getParentFile().mkdirs();
            FileUtils.copyFile(srcFile, destFile);

            return fullPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
