package core;

import config.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.WaitUtils;

import java.time.Duration;

public class BasePage {
    protected final AndroidDriver driver;

    public BasePage() {
        driver = DriverFactory.getDriver();
    }

    public void click(By locator) {
        WaitUtils.clickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = WaitUtils.visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void validateText(By locator, String expected) {
        String actual = getText(locator);
        Assert.assertEquals(actual, expected, "Unexpected text.");
    }

    public String getText(By locator) {
        return WaitUtils.visible(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        return WaitUtils.isVisible(locator, Duration.ofSeconds(30));
    }

    protected void fill(By locator, String value) {
        if (value != null && !value.isBlank()) {
            type(locator, value);
        }
    }
}