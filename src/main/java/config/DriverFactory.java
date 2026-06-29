package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

//MANAGES THE APPIUM ANDROIDDRIVER
public final class DriverFactory {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public static void createDriver() {
        if (driver != null) {
            return;
        }
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigManager.get("platformName"));
        options.setAutomationName(ConfigManager.get("automationName"));
        options.setDeviceName(ConfigManager.get("deviceName"));
        String udid = ConfigManager.get("udid");
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
        }
        String app = ConfigManager.get("app");
        if (app != null && !app.isBlank()) {
            //IF THE APK IS NOT INSTALLED
            options.setApp(app);
        } else {
            //OPENS AND APK INSTALLED ON THE PHONE
            String appPackage = ConfigManager.get("appPackage");
            String appActivity = ConfigManager.get("appActivity");
            if (appPackage != null && !appPackage.isBlank()) {
                options.setAppPackage(appPackage);
            }
            if (appActivity != null && !appActivity.isBlank()) {
                options.setAppActivity(appActivity);
            }

        }
        //CAPABILITIES
        options.setNoReset(Boolean.parseBoolean(ConfigManager.get("noReset")));
        options.setFullReset(Boolean.parseBoolean(ConfigManager.get("fullReset")));
        options.setAutoGrantPermissions(Boolean.parseBoolean(ConfigManager.get("autoGrantPermissions")));
        options.setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(ConfigManager.get("newCommandTimeout"))));
        //CONNECT TO THE SERVER
        try {
            driver = new AndroidDriver(URI.create(ConfigManager.get("server")).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium Server URL.", e);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create Appium Driver", e);
        }
    }

    //CLOSES THE DRIVER SESSION
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}