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

    public static WebElement presence(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static boolean invisible(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean textPresent(By locator, String text) {
        return getWait().until(ExpectedConditions.textToBe(locator, text));
    }

    public static WebElement visibilityOf(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement clickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement visible(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitUntilVisible(By locator, Duration timeout) {
        try {
            visible(locator, timeout);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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