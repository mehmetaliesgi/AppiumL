package utils.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties dosyası yüklenemedi!");
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property bulunamadı: " + key);
        }
        return value.trim();
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    public static String getAppiumPlatformName() {
        return getProperty("appium.platform.name");
    }

    public static String getAndroidPackageName() {
        return getProperty("android.package.name");
    }

    public static String getAndroidActivityName() {
        return getProperty("android.activity.name");
    }

    public static int getPoolingInterval() {
        return getIntProperty("pooling.interval");
    }

    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }

    public static String getTestUserEmail() {
        return getProperty("test.user.email");
    }

    public static String getTestUserPassword() {
        return getProperty("test.user.password");
    }

    public static boolean getAppiumNoReset() {
        return getBooleanProperty("appium.no.reset");
    }

    public static String getAppiumServerUrl(int threadId) {
        return getProperty("appium.server.url." + threadId);
    }

    public static int getAppiumServerPort(int threadId) {
        return getIntProperty("appium.server.port." + threadId);
    }


    public static int getSystemPort(int threadId) {
        return getIntProperty("appium.system.port." + threadId);
    }

    public static String getDeviceUdid(int threadId) {
        return getProperty("appium.device.udid." + threadId);
    }
}