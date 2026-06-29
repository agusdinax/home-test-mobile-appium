package utils;

import config.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {
    //waits reusable methods
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    private static AndroidDriver driver() {
        return DriverFactory.getDriver();
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(driver(), DEFAULT_TIMEOUT);
    }

    public static WebElement visible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean isVisible(By locator, Duration timeout) {
        try {
            new WebDriverWait(driver(), timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}